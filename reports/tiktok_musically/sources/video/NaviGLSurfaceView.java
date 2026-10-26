package com.ss.android.ugc.aweme.view.customView;

import X.C06980No;
import X.C0UH;
import X.C17200lG;
import X.C1IX;
import X.C1MP;
import X.C22090t9;
import X.C24590xB;
import X.C35030DoG;
import X.C35113Dpb;
import X.C35114Dpc;
import X.C4L6;
import X.HandlerC17190lF;
import X.RunnableC35088DpC;
import android.app.ActivityManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Size;
import android.view.MotionEvent;
import android.view.Surface;
import com.bef.effectsdk.BEFEffectNative;
import com.bef.effectsdk.OpenGLUtils;
import com.bytedance.covode.number.Covode;
import com.zhiliaoapp.musically.df_poi_map.R;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.List;
import java.util.Objects;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import kotlin.g.b.m;

/* JADX INFO: loaded from: classes6.dex */
public final class NaviGLSurfaceView extends GLSurfaceView implements SurfaceTexture.OnFrameAvailableListener, GLSurfaceView.Renderer {
    public Surface LIZ;
    public boolean LIZIZ;
    public final Handler LIZJ;
    public CameraDevice LIZLLL;
    public CameraCaptureSession LJ;
    public boolean LJFF;
    public String LJI;
    public boolean LJII;
    public int LJIIIIZZ;
    public int[] LJIIIZ;
    public SurfaceTexture LJIIJ;
    public final int[] LJIIJJI;
    public final float[] LJIIL;
    public final float[] LJIILIIL;
    public boolean LJIILJJIL;
    public boolean LJIILL;
    public boolean LJIILLIIL;
    public C35113Dpb LJIIZILJ;

    static {
        Covode.recordClassIndex(109405);
    }

    public final int getGLVersion() {
        return this.LJIIIIZZ;
    }

    public final C35113Dpb getNaviManager() {
        return this.LJIIZILJ;
    }

    @Override // android.opengl.GLSurfaceView
    public final void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= 21) {
            setCameraHW(this.LIZIZ);
        }
    }

    @Override // android.opengl.GLSurfaceView
    public final void onPause() {
        try {
            if (this.LJI != null && Build.VERSION.SDK_INT >= 21) {
                setCameraHW(false);
            }
            C35113Dpb c35113Dpb = this.LJIIZILJ;
            if (c35113Dpb == null) {
                m.LIZIZ();
            }
            c35113Dpb.LIZIZ();
            C35113Dpb c35113Dpb2 = this.LJIIZILJ;
            if (c35113Dpb2 == null) {
                m.LIZIZ();
            }
            c35113Dpb2.LIZLLL();
            C35113Dpb c35113Dpb3 = this.LJIIZILJ;
            if (c35113Dpb3 == null) {
                m.LIZIZ();
            }
            c35113Dpb3.LIZ();
            this.LJII = false;
        } catch (NullPointerException e) {
            C22090t9.LIZ(e);
        }
        super.onPause();
    }

    public final void setMultiTouchEnabled(boolean z) {
        this.LJIILJJIL = z;
    }

    public final void setTouchEnabled(boolean z) {
        this.LJIILL = z;
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public final synchronized void onFrameAvailable(SurfaceTexture surfaceTexture) {
        m.LIZLLL(surfaceTexture, "");
        this.LJIILLIIL = true;
        requestRender();
    }

    public final void setCamera(boolean z) {
        this.LIZIZ = z;
        if (Build.VERSION.SDK_INT >= 21) {
            setCameraHW(this.LIZIZ);
        }
        C4L6.LIZ(0L, new C35114Dpc(this));
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public final void onDrawFrame(GL10 gl10) {
        int i;
        int i2;
        int i3;
        int i4;
        m.LIZLLL(gl10, "");
        if (!this.LJII) {
            return;
        }
        synchronized (this) {
            if (this.LJIILLIIL) {
                try {
                    SurfaceTexture surfaceTexture = this.LJIIJ;
                    if (surfaceTexture == null) {
                        m.LIZIZ();
                    }
                    surfaceTexture.updateTexImage();
                } catch (Exception e) {
                    C22090t9.LIZ(e);
                }
                this.LJIILLIIL = false;
            }
        }
        try {
            C35113Dpb c35113Dpb = this.LJIIZILJ;
            if (c35113Dpb == null) {
                m.LIZIZ();
            }
            boolean z = this.LIZIZ;
            int i5 = this.LJIIIZ[0];
            int[] iArr = c35113Dpb.LJJIZ;
            if (iArr == null) {
                m.LIZIZ();
            }
            GLES20.glBindFramebuffer(36160, iArr[0]);
            GLES20.glClearColor(c35113Dpb.LJIIJJI, c35113Dpb.LJIIL, c35113Dpb.LJIILIIL, c35113Dpb.LJIILJJIL);
            GLES20.glClear(16384);
            if (c35113Dpb.LIZLLL != null) {
                Long l = c35113Dpb.LJ;
                long j = c35113Dpb.LIZIZ;
                if (l == null || l.longValue() != j) {
                    c35113Dpb.LIZ(c35113Dpb.LIZLLL);
                }
                if (z) {
                    if (c35113Dpb.LJIILLIIL) {
                        GLES20.glViewport(0, 0, c35113Dpb.LJII, c35113Dpb.LJIIIIZZ);
                        int[] iArr2 = c35113Dpb.LJJJJJ;
                        if (iArr2 == null) {
                            m.LIZIZ();
                        }
                        c35113Dpb.LIZ(i5, iArr2[0], 1.0f);
                    } else {
                        float f = (c35113Dpb.LJI * c35113Dpb.LJIIJ) / (c35113Dpb.LJFF * c35113Dpb.LJIIIZ);
                        GLES20.glViewport(0, 0, c35113Dpb.LJFF, c35113Dpb.LJI);
                        int[] iArr3 = c35113Dpb.LJJJJ;
                        if (iArr3 == null) {
                            m.LIZIZ();
                        }
                        c35113Dpb.LIZ(i5, iArr3[0], f);
                    }
                    GLES20.glFlush();
                }
                if (c35113Dpb.LJIILLIIL) {
                    int[] iArr4 = c35113Dpb.LJJJJJL;
                    if (iArr4 == null) {
                        m.LIZIZ();
                    }
                    i = iArr4[0];
                } else {
                    int[] iArr5 = c35113Dpb.LJJJJI;
                    if (iArr5 == null) {
                        m.LIZIZ();
                    }
                    i = iArr5[0];
                }
                if (c35113Dpb.LJIILLIIL) {
                    i2 = c35113Dpb.LJII;
                } else {
                    i2 = c35113Dpb.LJFF;
                }
                if (c35113Dpb.LJIILLIIL) {
                    i3 = c35113Dpb.LJIIIIZZ;
                } else {
                    i3 = c35113Dpb.LJI;
                }
                double dNanoTime = System.nanoTime();
                Double.isNaN(dNanoTime);
                double d = dNanoTime / 1.0E9d;
                BEFEffectNative.processAlgorithm(c35113Dpb.LIZIZ, i, i2, i3, d);
                if (c35113Dpb.LJIILLIIL) {
                    long j2 = c35113Dpb.LIZIZ;
                    int[] iArr6 = c35113Dpb.LJJJJJL;
                    if (iArr6 == null) {
                        m.LIZIZ();
                    }
                    int i6 = iArr6[0];
                    int[] iArr7 = c35113Dpb.LJJJIL;
                    if (iArr7 == null) {
                        m.LIZIZ();
                    }
                    BEFEffectNative.processFrame(j2, i6, iArr7[0], c35113Dpb.LJII, c35113Dpb.LJIIIIZZ, d);
                } else {
                    if (c35113Dpb.LJIIZILJ) {
                        int[] iArr8 = c35113Dpb.LJJJJI;
                        if (iArr8 == null) {
                            m.LIZIZ();
                        }
                        i4 = iArr8[0];
                    } else {
                        int[] iArr9 = c35113Dpb.LJJJ;
                        if (iArr9 == null) {
                            m.LIZIZ();
                        }
                        i4 = iArr9[0];
                    }
                    long j3 = c35113Dpb.LIZIZ;
                    int[] iArr10 = c35113Dpb.LJJJIL;
                    if (iArr10 == null) {
                        m.LIZIZ();
                    }
                    BEFEffectNative.processFrame(j3, i4, iArr10[0], c35113Dpb.LJFF, c35113Dpb.LJI, d);
                }
            } else {
                GLES20.glViewport(0, 0, c35113Dpb.LJFF, c35113Dpb.LJI);
                int[] iArr11 = c35113Dpb.LJJJ;
                if (iArr11 == null) {
                    m.LIZIZ();
                }
                int i7 = iArr11[0];
                int[] iArr12 = c35113Dpb.LJJJI;
                if (iArr12 == null) {
                    m.LIZIZ();
                }
                c35113Dpb.LIZ(i7, iArr12[0], 0.0f);
            }
            if (c35113Dpb.LJIJJ) {
                GLES20.glViewport(0, 0, c35113Dpb.LJIJJLI, c35113Dpb.LJIL);
                int[] iArr13 = c35113Dpb.LJJJIL;
                if (iArr13 == null) {
                    m.LIZIZ();
                }
                int i8 = iArr13[0];
                int[] iArr14 = c35113Dpb.LJJJJIZL;
                if (iArr14 == null) {
                    m.LIZIZ();
                }
                c35113Dpb.LIZ(i8, iArr14[0], 0.0f);
                GLES20.glFlush();
            }
            if (c35113Dpb.LJIILL) {
                GLES20.glViewport(0, 0, c35113Dpb.LJFF, c35113Dpb.LJI);
                int[] iArr15 = c35113Dpb.LJJJIL;
                if (iArr15 == null) {
                    m.LIZIZ();
                }
                c35113Dpb.LIZ(iArr15[0], 0, 0.0f);
                GLES20.glFlush();
            }
            if (c35113Dpb.LJIJJ) {
                c35113Dpb.LIZ.lock();
                if (c35113Dpb.LJIJJ && c35113Dpb.LJJ != null) {
                    int[] iArr16 = c35113Dpb.LJJJJIZL;
                    if (iArr16 == null) {
                        m.LIZIZ();
                    }
                    GLES20.glBindFramebuffer(36160, iArr16[0]);
                    Bitmap bitmapLIZ = c35113Dpb.LIZ(c35113Dpb.LJIJJLI, c35113Dpb.LJIL, true);
                    List<ByteArrayOutputStream> list = c35113Dpb.LJJ;
                    if (list == null) {
                        m.LIZIZ();
                    }
                    int size = list.size();
                    List<ByteArrayOutputStream> list2 = c35113Dpb.LJJ;
                    if (list2 == null) {
                        m.LIZIZ();
                    }
                    list2.add(new ByteArrayOutputStream());
                    Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.WEBP;
                    List<ByteArrayOutputStream> list3 = c35113Dpb.LJJ;
                    if (list3 == null) {
                        m.LIZIZ();
                    }
                    bitmapLIZ.compress(compressFormat, 90, list3.get(size));
                }
                c35113Dpb.LIZ.unlock();
            }
            C1IX<C24590xB> c1ix = c35113Dpb.LJJJJL;
            if (c1ix != null) {
                c1ix.invoke();
            }
        } catch (Exception e2) {
            C22090t9.LIZ(e2);
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        m.LIZLLL(motionEvent, "");
        if (!this.LJIILL) {
            return super.onTouchEvent(motionEvent);
        }
        int pointerCount = motionEvent.getPointerCount();
        if (pointerCount > 10) {
            pointerCount = 10;
        }
        for (int i = 0; i < pointerCount; i++) {
            this.LJIIJJI[i] = motionEvent.getPointerId(i);
            this.LJIIL[i] = motionEvent.getX(i);
            this.LJIILIIL[i] = motionEvent.getY(i);
        }
        int action = motionEvent.getAction() & 255;
        if (action != 0) {
            if (action != 1) {
                if (action != 2) {
                    if (action != 5) {
                        if (action == 6) {
                            int action2 = motionEvent.getAction() >> 8;
                            if (this.LJIILJJIL || action2 == 0) {
                                LIZ(this.LJIIL, this.LJIILIIL, pointerCount);
                                int[] iArr = {this.LJIIJJI[action2]};
                                float[] fArr = {this.LJIIL[action2]};
                                float[] fArr2 = {this.LJIILIIL[action2]};
                                C35113Dpb c35113Dpb = this.LJIIZILJ;
                                if (c35113Dpb == null) {
                                    m.LIZIZ();
                                }
                                c35113Dpb.LIZJ(iArr, fArr, fArr2);
                            }
                        }
                    } else {
                        int action3 = motionEvent.getAction() >> 8;
                        if (this.LJIILJJIL || action3 == 0) {
                            LIZ(this.LJIIL, this.LJIILIIL, pointerCount);
                            int[] iArr2 = {this.LJIIJJI[action3]};
                            float[] fArr3 = {this.LJIIL[action3]};
                            float[] fArr4 = {this.LJIILIIL[action3]};
                            C35113Dpb c35113Dpb2 = this.LJIIZILJ;
                            if (c35113Dpb2 == null) {
                                m.LIZIZ();
                            }
                            c35113Dpb2.LIZ(iArr2, fArr3, fArr4);
                        }
                    }
                } else {
                    LIZ(this.LJIIL, this.LJIILIIL, pointerCount);
                    if (!this.LJIILJJIL) {
                        int[] iArr3 = {this.LJIIJJI[0]};
                        float[] fArr5 = {this.LJIIL[0]};
                        float[] fArr6 = {this.LJIILIIL[0]};
                        C35113Dpb c35113Dpb3 = this.LJIIZILJ;
                        if (c35113Dpb3 == null) {
                            m.LIZIZ();
                        }
                        c35113Dpb3.LIZIZ(iArr3, fArr5, fArr6);
                    } else {
                        for (int i2 = 0; i2 < pointerCount; i2++) {
                            int[] iArr4 = {this.LJIIJJI[i2]};
                            float[] fArr7 = {this.LJIIL[i2]};
                            float[] fArr8 = {this.LJIILIIL[i2]};
                            C35113Dpb c35113Dpb4 = this.LJIIZILJ;
                            if (c35113Dpb4 == null) {
                                m.LIZIZ();
                            }
                            c35113Dpb4.LIZIZ(iArr4, fArr7, fArr8);
                        }
                    }
                }
            } else {
                LIZ(this.LJIIL, this.LJIILIIL, 1);
                int[] iArr5 = {this.LJIIJJI[0]};
                float[] fArr9 = {this.LJIIL[0]};
                float[] fArr10 = {this.LJIILIIL[0]};
                C35113Dpb c35113Dpb5 = this.LJIIZILJ;
                if (c35113Dpb5 == null) {
                    m.LIZIZ();
                }
                c35113Dpb5.LIZJ(iArr5, fArr9, fArr10);
            }
        } else {
            LIZ(this.LJIIL, this.LJIILIIL, 1);
            int[] iArr6 = {this.LJIIJJI[0]};
            float[] fArr11 = {this.LJIIL[0]};
            float[] fArr12 = {this.LJIILIIL[0]};
            C35113Dpb c35113Dpb6 = this.LJIIZILJ;
            if (c35113Dpb6 == null) {
                m.LIZIZ();
            }
            c35113Dpb6.LIZ(iArr6, fArr11, fArr12);
        }
        return true;
    }

    public final void setCameraHW(boolean z) {
        boolean z2 = true;
        if (!m.LIZ(Looper.myLooper(), Looper.getMainLooper())) {
            this.LIZJ.post(new RunnableC35088DpC(this, z));
            return;
        }
        if (this.LJII) {
            if (this.LJI == null) {
                z2 = false;
            }
            if (z == z2) {
                return;
            }
            if (z) {
                Object objLIZ = LIZ(getContext(), "camera");
                Objects.requireNonNull(objLIZ, "null cannot be cast to non-null type android.hardware.camera2.CameraManager");
                CameraManager cameraManager = (CameraManager) objLIZ;
                try {
                    for (String str : cameraManager.getCameraIdList()) {
                        CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(str);
                        m.LIZIZ(cameraCharacteristics, "");
                        Integer num = (Integer) cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
                        if (num != null && num.intValue() == 0) {
                            this.LJI = str;
                            StreamConfigurationMap streamConfigurationMap = (StreamConfigurationMap) cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                            if (streamConfigurationMap == null) {
                                m.LIZIZ();
                            }
                            for (Size size : streamConfigurationMap.getOutputSizes(SurfaceTexture.class)) {
                                m.LIZIZ(size, "");
                                if (1920 == size.getWidth() && 1080 == size.getHeight()) {
                                    break;
                                }
                            }
                            break;
                        }
                    }
                } catch (Exception e) {
                    C22090t9.LIZ(e);
                }
                Object objLIZ2 = LIZ(getContext(), "camera");
                Objects.requireNonNull(objLIZ2, "null cannot be cast to non-null type android.hardware.camera2.CameraManager");
                CameraManager cameraManager2 = (CameraManager) objLIZ2;
                try {
                    String str2 = this.LJI;
                    if (str2 == null) {
                        return;
                    }
                    cameraManager2.openCamera(str2, new C35030DoG(this), this.LIZJ);
                    return;
                } catch (Exception e2) {
                    C22090t9.LIZ(e2);
                    return;
                }
            }
            try {
                CameraCaptureSession cameraCaptureSession = this.LJ;
                if (cameraCaptureSession != null) {
                    if (cameraCaptureSession == null) {
                        try {
                            m.LIZIZ();
                        } catch (Exception e3) {
                            C22090t9.LIZ(e3);
                        }
                    }
                    cameraCaptureSession.stopRepeating();
                    CameraCaptureSession cameraCaptureSession2 = this.LJ;
                    if (cameraCaptureSession2 == null) {
                        m.LIZIZ();
                    }
                    cameraCaptureSession2.close();
                    this.LJ = null;
                }
                if (this.LJI != null) {
                    this.LJI = null;
                }
                CameraDevice cameraDevice = this.LIZLLL;
                if (cameraDevice == null) {
                    return;
                }
                if (cameraDevice == null) {
                    m.LIZIZ();
                }
                if (!((Boolean) C0UH.LIZ(cameraDevice, new Object[0], 100201, "void", false, null).first).booleanValue()) {
                    C0UH.LIZ(null, cameraDevice, new Object[0], 100205, "com_ss_android_ugc_aweme_view_customView_NaviGLSurfaceView_android_hardware_camera2_CameraDevice_close(Landroid/hardware/camera2/CameraDevice;)V");
                    cameraDevice.close();
                    C0UH.LIZ(null, cameraDevice, new Object[0], 100201, "com_ss_android_ugc_aweme_view_customView_NaviGLSurfaceView_android_hardware_camera2_CameraDevice_close(Landroid/hardware/camera2/CameraDevice;)V");
                }
                this.LIZLLL = null;
            } catch (InterruptedException e4) {
                C22090t9.LIZ(e4);
            }
        }
    }

    public static Object LIZ(Context context, String str) {
        Object systemService;
        if (Build.VERSION.SDK_INT <= 27 && "clipboard".equals(str)) {
            if (C17200lG.LIZ) {
                synchronized (ClipboardManager.class) {
                    systemService = context.getSystemService(str);
                    if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                        try {
                            Field declaredField = ClipboardManager.class.getDeclaredField("mHandler");
                            declaredField.setAccessible(true);
                            declaredField.set(systemService, new HandlerC17190lF((Handler) declaredField.get(systemService)));
                        } catch (Exception e) {
                            C06980No.LIZ(e, "ClipboardManager Handler Reflect Fail");
                        }
                        C17200lG.LIZ = false;
                    } else {
                        C17200lG.LIZ = false;
                    }
                    throw th;
                }
                return systemService;
            }
            return context.getSystemService(str);
        }
        if (!C17200lG.LIZIZ && "connectivity".equals(str)) {
            try {
                new C1MP().LIZ();
                C17200lG.LIZIZ = true;
                return context.getSystemService(str);
            } catch (Throwable unused) {
            }
        }
        return context.getSystemService(str);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NaviGLSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m.LIZLLL(context, "");
        this.LJIIIZ = new int[1];
        this.LJIIJJI = new int[10];
        this.LJIIL = new float[10];
        this.LJIILIIL = new float[10];
        this.LIZJ = new Handler(Looper.getMainLooper());
        this.LJFF = true;
        Object objLIZ = LIZ(context, "activity");
        Objects.requireNonNull(objLIZ, "null cannot be cast to non-null type android.app.ActivityManager");
        ConfigurationInfo deviceConfigurationInfo = ((ActivityManager) objLIZ).getDeviceConfigurationInfo();
        boolean z = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.axx, R.attr.axy}).getBoolean(1, true);
        int i = deviceConfigurationInfo.reqGlEsVersion >= 196608 ? 3 : 2;
        this.LJIIIIZZ = i;
        setEGLContextClientVersion(i);
        setZOrderOnTop(z);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        getHolder().setFormat(1);
        setRenderer(this);
        setRenderMode(1);
        this.LJIIZILJ = new C35113Dpb();
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public final void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        boolean z;
        m.LIZLLL(gl10, "");
        m.LIZLLL(eGLConfig, "");
        if (!this.LJII) {
            C35113Dpb c35113Dpb = this.LJIIZILJ;
            if (c35113Dpb == null) {
                m.LIZIZ();
            }
            c35113Dpb.LJJIIJZLJL = OpenGLUtils.loadProgram("attribute vec2 vPosition;\nattribute vec2 vTexCoord;\nvarying vec2 texCoord;\nvoid main() {\n  texCoord = vTexCoord;\n  gl_Position = vec4(vPosition.x, vPosition.y, 0.0, 1.0);\n}", "precision mediump float;\nuniform sampler2D sTexture;\nvarying vec2 texCoord;\nvoid main() {\n  gl_FragColor = texture2D(sTexture, texCoord);\n}");
            c35113Dpb.LJJIIZ = GLES20.glGetAttribLocation(c35113Dpb.LJJIIJZLJL, "vTexCoord");
            c35113Dpb.LJJIIZI = GLES20.glGetAttribLocation(c35113Dpb.LJJIIJZLJL, "vPosition");
            c35113Dpb.LJJIJ = GLES20.glGetUniformLocation(c35113Dpb.LJJIIJZLJL, "sTexture");
            c35113Dpb.LJJI = OpenGLUtils.loadProgram("attribute vec2 vPosition;\nattribute vec2 vTexCoord;\nvarying vec2 texCoord;\nvoid main() {\n  texCoord = vTexCoord;\n  gl_Position = vec4(vPosition.x, vPosition.y, 0.0, 1.0);\n}", "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nuniform samplerExternalOES sCamera;\nuniform float uCamera;\nvarying vec2 texCoord;\nvoid main() {\n  vec2 uv = texCoord;\n  float off = (1.0 - uCamera) / 2.0;\n  uv.x = uv.x * uCamera + off;\n  gl_FragColor = texture2D(sCamera, uv);\n}");
            c35113Dpb.LJJIII = GLES20.glGetAttribLocation(c35113Dpb.LJJI, "vTexCoord");
            c35113Dpb.LJJII = GLES20.glGetAttribLocation(c35113Dpb.LJJI, "vPosition");
            c35113Dpb.LJJIFFI = GLES20.glGetUniformLocation(c35113Dpb.LJJI, "sCamera");
            c35113Dpb.LJJIIJ = GLES20.glGetUniformLocation(c35113Dpb.LJJI, "uCamera");
            ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(C35113Dpb.LJJJJLI.length * 4);
            byteBufferAllocateDirect.order(ByteOrder.nativeOrder());
            c35113Dpb.LJJIJL = byteBufferAllocateDirect.asFloatBuffer();
            FloatBuffer floatBuffer = c35113Dpb.LJJIJL;
            if (floatBuffer != null) {
                floatBuffer.put(C35113Dpb.LJJJJLI);
            }
            FloatBuffer floatBuffer2 = c35113Dpb.LJJIJL;
            if (floatBuffer2 != null) {
                floatBuffer2.position(0);
            }
            c35113Dpb.LJJIJIIJI = new int[1];
            GLES20.glGenBuffers(1, c35113Dpb.LJJIJIIJI, 0);
            int[] iArr = c35113Dpb.LJJIJIIJI;
            if (iArr == null) {
                m.LIZIZ();
            }
            GLES20.glBindBuffer(34962, iArr[0]);
            GLES20.glBufferData(34962, C35113Dpb.LJJJJLI.length * 4, c35113Dpb.LJJIJL, 35044);
            ByteBuffer byteBufferAllocateDirect2 = ByteBuffer.allocateDirect(C35113Dpb.LJJJJLL.length * 4);
            byteBufferAllocateDirect2.order(ByteOrder.nativeOrder());
            c35113Dpb.LJJIJLIJ = byteBufferAllocateDirect2.asFloatBuffer();
            FloatBuffer floatBuffer3 = c35113Dpb.LJJIJLIJ;
            if (floatBuffer3 != null) {
                floatBuffer3.put(C35113Dpb.LJJJJLL);
            }
            FloatBuffer floatBuffer4 = c35113Dpb.LJJIJLIJ;
            if (floatBuffer4 != null) {
                floatBuffer4.position(0);
            }
            c35113Dpb.LJJIJIIJIL = new int[1];
            GLES20.glGenBuffers(1, c35113Dpb.LJJIJIIJIL, 0);
            int[] iArr2 = c35113Dpb.LJJIJIIJIL;
            if (iArr2 == null) {
                m.LIZIZ();
            }
            GLES20.glBindBuffer(34962, iArr2[0]);
            GLES20.glBufferData(34962, C35113Dpb.LJJJJLL.length * 4, c35113Dpb.LJJIJLIJ, 35044);
            ByteBuffer byteBufferAllocateDirect3 = ByteBuffer.allocateDirect(C35113Dpb.LJJJJZ.length * 2);
            byteBufferAllocateDirect3.order(ByteOrder.nativeOrder());
            c35113Dpb.LJJIL = byteBufferAllocateDirect3.asShortBuffer();
            ShortBuffer shortBuffer = c35113Dpb.LJJIL;
            if (shortBuffer != null) {
                shortBuffer.put(C35113Dpb.LJJJJZ);
            }
            ShortBuffer shortBuffer2 = c35113Dpb.LJJIL;
            if (shortBuffer2 != null) {
                shortBuffer2.position(0);
            }
            c35113Dpb.LJJIJIL = new int[1];
            GLES20.glGenBuffers(1, c35113Dpb.LJJIJIL, 0);
            int[] iArr3 = c35113Dpb.LJJIJIL;
            if (iArr3 == null) {
                m.LIZIZ();
            }
            GLES20.glBindBuffer(34963, iArr3[0]);
            GLES20.glBufferData(34963, C35113Dpb.LJJJJZ.length * 2, c35113Dpb.LJJIL, 35044);
            C35113Dpb c35113Dpb2 = this.LJIIZILJ;
            if (c35113Dpb2 == null) {
                m.LIZIZ();
            }
            c35113Dpb2.LIZJ();
            C35113Dpb c35113Dpb3 = this.LJIIZILJ;
            if (c35113Dpb3 == null) {
                m.LIZIZ();
            }
            if (this.LJIIIIZZ == 3) {
                z = true;
            } else {
                z = false;
            }
            c35113Dpb3.LIZ();
            c35113Dpb3.LIZIZ = BEFEffectNative.createHandle(z);
            if (c35113Dpb3.LIZIZ != 0 && c35113Dpb3.LIZJ != null) {
                BEFEffectNative.initResourceFinder(c35113Dpb3.LIZIZ, c35113Dpb3.LIZJ, c35113Dpb3.LJIIJ, c35113Dpb3.LJIIIZ, "android");
                BEFEffectNative.setOrientation(c35113Dpb3.LIZIZ, 0);
                BEFEffectNative.setFrameOrientation(c35113Dpb3.LIZIZ, 0);
                BEFEffectNative.setCameraPosition(c35113Dpb3.LIZIZ, true);
            }
            this.LJIIIZ = new int[1];
            gl10.glDisable(3024);
            gl10.glHint(3152, 4353);
            gl10.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            gl10.glEnable(2884);
            gl10.glShadeModel(7425);
            gl10.glEnable(2929);
            GLES20.glGenTextures(1, this.LJIIIZ, 0);
            GLES20.glBindTexture(36197, this.LJIIIZ[0]);
            GLES20.glTexParameteri(36197, 10242, 33071);
            GLES20.glTexParameteri(36197, 10243, 33071);
            GLES20.glTexParameteri(36197, 10241, 9729);
            GLES20.glTexParameteri(36197, 10240, 9729);
            SurfaceTexture surfaceTexture = new SurfaceTexture(this.LJIIIZ[0]);
            this.LJIIJ = surfaceTexture;
            if (surfaceTexture == null) {
                m.LIZIZ();
            }
            surfaceTexture.setDefaultBufferSize(1920, 1080);
            SurfaceTexture surfaceTexture2 = this.LJIIJ;
            if (surfaceTexture2 == null) {
                m.LIZIZ();
            }
            surfaceTexture2.setOnFrameAvailableListener(this);
            this.LIZ = new Surface(this.LJIIJ);
            this.LJII = true;
            if (Build.VERSION.SDK_INT >= 21) {
                setCameraHW(this.LIZIZ);
            }
        }
    }

    private final void LIZ(float[] fArr, float[] fArr2, int i) {
        int width = getWidth();
        int height = getHeight();
        for (int i2 = 0; i2 < i; i2++) {
            float f = fArr[i2];
            float f2 = fArr2[i2];
            fArr[i2] = f / width;
            fArr2[i2] = 1.0f - (f2 / height);
        }
    }

    @Override // android.opengl.GLSurfaceView.Renderer
    public final void onSurfaceChanged(GL10 gl10, int i, int i2) {
        m.LIZLLL(gl10, "");
        C35113Dpb c35113Dpb = this.LJIIZILJ;
        if (c35113Dpb == null) {
            m.LIZIZ();
        }
        c35113Dpb.LJFF = i;
        c35113Dpb.LJI = i2;
        C35113Dpb c35113Dpb2 = this.LJIIZILJ;
        if (c35113Dpb2 == null) {
            m.LIZIZ();
        }
        c35113Dpb2.LIZJ();
    }
}
