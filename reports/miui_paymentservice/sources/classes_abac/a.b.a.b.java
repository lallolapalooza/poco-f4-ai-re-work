package a.b.a;

import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* JADX INFO: compiled from: CameraManager.java */
/* JADX INFO: loaded from: classes.dex */
public class b {
    private static final int l = 1;
    private static final int m = 2;
    private static final int n = 300;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Camera f389b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a.b.a.c.C0010c f390c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private h f391d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f392e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f393f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final String f388a = "CameraManager";
    private final Camera.AutoFocusCallback g = new a();
    private final Handler h = new HandlerC0009b();
    private final Handler i = new c();
    private Camera.PreviewCallback j = new d();
    private g k = new e();

    /* JADX INFO: compiled from: CameraManager.java */
    class a implements Camera.AutoFocusCallback {
        a() {
        }

        @Override // android.hardware.Camera.AutoFocusCallback
        public void onAutoFocus(boolean z, Camera camera) {
            if (z && b.this.d()) {
                b.this.f389b.setOneShotPreviewCallback(b.this.j);
            } else {
                b.this.h.sendEmptyMessageDelayed(2, 300L);
            }
        }
    }

    /* JADX INFO: renamed from: a.b.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CameraManager.java */
    class HandlerC0009b extends Handler {
        HandlerC0009b() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 2 && b.this.f389b != null) {
                b.this.f389b.autoFocus(b.this.g);
            }
        }
    }

    /* JADX INFO: compiled from: CameraManager.java */
    class c extends Handler {
        c() {
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            if (hasMessages(1)) {
                removeMessages(1);
            }
            if (b.this.c()) {
                b.this.h.sendEmptyMessageDelayed(2, 300L);
            } else if (b.this.f389b == null || !b.this.d()) {
                sendEmptyMessageDelayed(1, 300L);
            } else {
                b.this.f389b.setOneShotPreviewCallback(b.this.j);
            }
        }
    }

    /* JADX INFO: compiled from: CameraManager.java */
    class d implements Camera.PreviewCallback {
        d() {
        }

        @Override // android.hardware.Camera.PreviewCallback
        public void onPreviewFrame(byte[] bArr, Camera camera) {
            if (b.this.f391d != null) {
                b.this.f391d.a(bArr);
            }
        }
    }

    /* JADX INFO: compiled from: CameraManager.java */
    class e implements g {
        e() {
        }

        @Override // a.b.a.b.g
        public void a() {
            b.this.i.sendEmptyMessage(1);
        }
    }

    /* JADX INFO: compiled from: CameraManager.java */
    class f implements Comparator<Camera.Size> {
        f() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(Camera.Size size, Camera.Size size2) {
            int i = size.height * size.width;
            int i2 = size2.height * size2.width;
            if (i2 < i) {
                return -1;
            }
            return i2 > i ? 1 : 0;
        }
    }

    /* JADX INFO: compiled from: CameraManager.java */
    public interface g {
        void a();
    }

    /* JADX INFO: compiled from: CameraManager.java */
    public interface h {
        void a(byte[] bArr);
    }

    public void g() {
        Camera camera = this.f389b;
        if (camera == null) {
            Log.d("CameraManager", "my Camera is not opened from doResume");
        } else {
            camera.startPreview();
            this.f393f = true;
        }
    }

    public void a(h hVar) {
        this.f391d = hVar;
    }

    public g b() {
        return this.k;
    }

    public boolean c() {
        return this.f392e;
    }

    public boolean d() {
        return this.f393f;
    }

    public boolean e() {
        if (this.f389b == null) {
            try {
                this.f389b = Camera.open();
            } catch (Exception unused) {
                return false;
            }
        }
        return this.f389b != null;
    }

    public void f() {
        if (this.f389b != null) {
            this.h.removeMessages(2);
            this.i.removeMessages(1);
            this.f389b.stopPreview();
        }
        this.f393f = false;
    }

    public void a() {
        if (this.f389b != null) {
            this.h.removeMessages(2);
            this.i.removeMessages(1);
            this.f389b.release();
            this.f389b = null;
        }
        this.f393f = false;
    }

    public void a(SurfaceHolder surfaceHolder) {
        Camera camera = this.f389b;
        if (camera != null) {
            try {
                camera.setPreviewDisplay(surfaceHolder);
                return;
            } catch (IOException e2) {
                Log.e("CameraManager", "IOException caused by setPreviewDisplay()", e2);
                return;
            }
        }
        Log.d("CameraManager", "my Camera is not opened, from setPreviewDisplay");
    }

    public void a(a.b.a.c.C0010c c0010c, int i) {
        Camera.Parameters parameters = this.f389b.getParameters();
        parameters.setPreviewSize(c0010c.f407a, c0010c.f408b);
        if (parameters.getSupportedFocusModes().contains("continuous-picture")) {
            parameters.setFocusMode("continuous-picture");
            this.f392e = false;
        } else {
            this.f392e = true;
            parameters.setFocusMode("auto");
        }
        this.f389b.setParameters(parameters);
        this.f389b.setDisplayOrientation(i);
    }

    public a.b.a.c.C0010c a(a.b.a.c.C0010c c0010c) {
        if (this.f389b == null) {
            return null;
        }
        a.b.a.c.C0010c c0010c2 = this.f390c;
        if (c0010c2 != null) {
            return c0010c2;
        }
        int i = c0010c.f407a;
        int i2 = c0010c.f408b;
        if (i < i2) {
            i = i2;
        }
        int i3 = c0010c.f407a;
        int i4 = c0010c.f408b;
        if (i3 < i4) {
            i4 = i3;
        }
        ArrayList<Camera.Size> arrayList = new ArrayList(this.f389b.getParameters().getSupportedPreviewSizes());
        Collections.sort(arrayList, new f());
        if (Log.isLoggable("CameraManager", 4)) {
            StringBuilder sb = new StringBuilder();
            for (Camera.Size size : arrayList) {
                sb.append(size.width);
                sb.append('x');
                sb.append(size.height);
                sb.append(' ');
            }
            Log.i("CameraManager", "Supported preview sizes: " + ((Object) sb));
        }
        float f2 = i / i4;
        float f3 = Float.POSITIVE_INFINITY;
        for (Camera.Size size2 : arrayList) {
            int i5 = size2.width;
            int i6 = size2.height;
            boolean z = i5 < i6;
            int i7 = z ? i6 : i5;
            int i8 = z ? i5 : i6;
            if (i7 == i && i8 == i4) {
                a.b.a.c.C0010c c0010c3 = new a.b.a.c.C0010c(i5, i6);
                Log.i("CameraManager", "Found preview size exactly matching screen size: " + c0010c3);
                this.f390c = c0010c3;
                break;
            }
            float fAbs = Math.abs((i7 / i8) - f2);
            if (fAbs < f3) {
                this.f390c = new a.b.a.c.C0010c(i5, i6);
                f3 = fAbs;
            }
        }
        Log.i("CameraManager", "Found best approximate preview size: " + this.f390c);
        return this.f390c;
    }
}
