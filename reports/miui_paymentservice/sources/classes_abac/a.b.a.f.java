package a.b.a;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import java.util.ArrayList;

/* JADX INFO: compiled from: RecognizeController.java */
/* JADX INFO: loaded from: classes.dex */
public class f {
    private static final int n = 1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f413a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f414b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f415c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f416d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f417e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Point f418f;
    private d g;
    private c.b h;
    private b i;
    private a.b.a.b.g j;
    private Context k;
    private boolean l = false;
    private a.b.a.b.h m = new a();

    /* JADX INFO: compiled from: RecognizeController.java */
    class a implements a.b.a.b.h {
        a() {
        }

        @Override // a.b.a.b.h
        public void a(byte[] bArr) {
            if (bArr == null || f.this.l) {
                return;
            }
            if (f.this.i.a() && f.this.g.a()) {
                f.this.a(bArr);
            } else if (f.this.j != null) {
                f.this.j.a();
            }
        }
    }

    /* JADX INFO: compiled from: RecognizeController.java */
    private class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f420a;

        public b(Looper looper) {
            super(looper);
            this.f420a = true;
        }

        public boolean a() {
            return this.f420a && !hasMessages(1);
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            if (f.this.l || f.this.g == null || message.what != 1) {
                return;
            }
            this.f420a = false;
            String strA = f.this.g.a((byte[]) message.obj, f.this.f413a, f.this.f414b, f.this.f418f, f.this.f415c, f.this.f416d, f.this.f417e);
            if (!TextUtils.isEmpty(strA)) {
                f.this.h.a(strA);
                f.this.l = true;
            } else if (f.this.j != null) {
                f.this.j.a();
            }
            this.f420a = true;
        }
    }

    public f(Context context, c.b bVar) {
        this.k = context;
        this.h = bVar;
    }

    public void b() {
        this.l = false;
        a.b.a.b.g gVar = this.j;
        if (gVar != null) {
            gVar.a();
        }
    }

    public void c() {
        this.l = false;
        this.g = new d();
        this.g.a(this.k);
        HandlerThread handlerThread = new HandlerThread("decode");
        handlerThread.start();
        this.i = new b(handlerThread.getLooper());
    }

    public void d() {
        this.i.removeMessages(1);
        if (this.j != null) {
            this.j = null;
        }
    }

    public void a(a.b.a.b.g gVar) {
        this.j = gVar;
    }

    public a.b.a.b.h a() {
        return this.m;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(byte[] bArr) {
        if (this.i.a()) {
            Message messageObtainMessage = this.i.obtainMessage(1);
            messageObtainMessage.obj = bArr;
            messageObtainMessage.sendToTarget();
        }
    }

    public void a(ArrayList<Integer> arrayList) {
        this.g.a(arrayList);
    }

    public void a(int i, int i2, Point point, int i3, int i4, int i5) {
        this.f413a = i;
        this.f414b = i2;
        this.f418f = point;
        this.f415c = i3;
        this.f416d = i4;
        this.f417e = i5;
    }
}
