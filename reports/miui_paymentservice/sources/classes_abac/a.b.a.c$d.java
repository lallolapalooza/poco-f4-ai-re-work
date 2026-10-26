package a.b.a;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import android.widget.Toast;
import com.mibi.ocr.ui.CameraPreview;
import java.util.ArrayList;

/* JADX INFO: compiled from: DigitOCRManager.java */
/* JADX INFO: loaded from: classes.dex */
public class c {
    private static final String l = "DigitOCRManager";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f400a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private SurfaceHolder f401b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private f f403d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private b f404e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Rect f405f;
    private C0010c g;
    private C0010c h;
    private int i;
    private d j = d.NULL;
    private CameraPreview.a k = new a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a.b.a.b f402c = new a.b.a.b();

    /* JADX INFO: compiled from: DigitOCRManager.java */
    class a implements CameraPreview.a {
        a() {
        }

        @Override // com.mibi.ocr.ui.CameraPreview.a
        public void a(SurfaceHolder surfaceHolder) {
            c.this.f401b = null;
        }

        @Override // com.mibi.ocr.ui.CameraPreview.a
        public void a(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }

        @Override // com.mibi.ocr.ui.CameraPreview.a
        public void b(SurfaceHolder surfaceHolder) {
            c.this.f401b = surfaceHolder;
            c.this.f402c.a(surfaceHolder);
            c.this.f402c.g();
        }
    }

    /* JADX INFO: compiled from: DigitOCRManager.java */
    public interface b {
        void a(String str);
    }

    /* JADX INFO: renamed from: a.b.a.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DigitOCRManager.java */
    public static class C0010c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f407a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f408b;

        public C0010c(int i, int i2) {
            this.f407a = i;
            this.f408b = i2;
        }
    }

    /* JADX INFO: compiled from: DigitOCRManager.java */
    private enum d {
        NULL,
        INIT,
        PREVIEWING,
        PAUSED
    }

    public c(Context context) {
        this.f400a = context;
        j();
    }

    private void j() {
        Display defaultDisplay = ((WindowManager) this.f400a.getApplicationContext().getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        this.g = new C0010c(point.x, point.y);
        this.i = b(defaultDisplay.getRotation());
    }

    public CameraPreview.a b() {
        return this.k;
    }

    public int c() {
        return this.i;
    }

    public C0010c d() {
        return this.g;
    }

    public boolean e() {
        return this.j == d.PREVIEWING;
    }

    public void f() {
        this.f402c.f();
        this.j = d.PAUSED;
    }

    public void g() {
        d dVar = d.INIT;
        d dVar2 = this.j;
        if (dVar == dVar2 || d.PAUSED == dVar2) {
            if (this.f401b != null && !this.f402c.d()) {
                this.f402c.a(this.f401b);
                this.f402c.g();
            }
            this.f403d.b();
            this.j = d.PREVIEWING;
        }
    }

    public boolean h() {
        if (!this.f402c.e()) {
            Log.e(l, "OCRManager dostart open camera failed");
            Context context = this.f400a;
            Toast.makeText(context, context.getResources().getText(e.q.mibi_camera_open_failed), 0).show();
            return false;
        }
        this.h = this.f402c.a(this.g);
        if (this.f403d == null) {
            this.f403d = new f(this.f400a, this.f404e);
            this.f402c.a(this.f403d.a());
            this.f403d.a(this.f402c.b());
            this.f403d.c();
        }
        a(this.h);
        return true;
    }

    public void i() {
        f fVar = this.f403d;
        if (fVar != null) {
            fVar.d();
            this.f403d = null;
        }
        this.f402c.a();
    }

    /* JADX WARN: Code duplicated, block: B:10:0x000e  */
    private int b(int i) {
        int i2;
        if (i == 0) {
            i2 = 0;
        } else if (i == 1) {
            i2 = 90;
        } else if (i == 2) {
            i2 = 180;
        } else if (i != 3) {
            i2 = 0;
        } else {
            i2 = 270;
        }
        return ((360 - i2) + 90) % 360;
    }

    public void a() {
        this.f404e = null;
    }

    public void a(b bVar) {
        this.f404e = bVar;
    }

    public void a(ArrayList<Integer> arrayList) {
        this.f403d.a(arrayList);
    }

    public void a(int i) {
        this.i = i;
    }

    public void a(Rect rect) {
        this.f405f = rect;
    }

    private void a(C0010c c0010c) {
        this.f402c.a(c0010c, this.i);
        this.j = d.INIT;
        Rect rect = this.f405f;
        if (rect == null) {
            Log.e(l, "crop frame is null");
        } else {
            Rect rectA = a(c0010c, this.g, rect);
            this.f403d.a(c0010c.f407a, c0010c.f408b, new Point(rectA.left, rectA.top), rectA.right - rectA.left, rectA.bottom - rectA.top, this.i);
        }
    }

    private Rect a(C0010c c0010c, C0010c c0010c2, Rect rect) {
        if (c0010c == null || c0010c2 == null) {
            return null;
        }
        Rect rect2 = new Rect(rect);
        if (this.f400a.getResources().getConfiguration().orientation == 1) {
            int i = rect.left;
            int i2 = c0010c.f408b;
            int i3 = c0010c2.f407a;
            rect2.left = (i * i2) / i3;
            rect2.right = (rect.right * i2) / i3;
            int i4 = rect.top;
            int i5 = c0010c.f407a;
            int i6 = c0010c2.f408b;
            rect2.top = (i4 * i5) / i6;
            rect2.bottom = (rect.bottom * i5) / i6;
        } else {
            int i7 = rect.left;
            int i8 = c0010c.f407a;
            int i9 = c0010c2.f407a;
            rect2.left = (i7 * i8) / i9;
            rect2.right = (rect.right * i8) / i9;
            int i10 = rect.top;
            int i11 = c0010c.f408b;
            int i12 = c0010c2.f408b;
            rect2.top = (i10 * i11) / i12;
            rect2.bottom = (rect.bottom * i11) / i12;
        }
        return rect2;
    }
}
