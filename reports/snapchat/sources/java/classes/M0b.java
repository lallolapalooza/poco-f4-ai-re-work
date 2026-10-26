package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class M0b extends View {
    public final ArrayList R;
    public final C2876Foa S;
    public final C2876Foa T;
    public InterfaceC38474uDg U;
    public final ArrayList a;
    public final ArrayList b;
    public final ArrayList c;

    public M0b(Context context) {
        super(context);
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.c = new ArrayList();
        this.R = new ArrayList();
        this.S = new C2876Foa();
        this.T = new C2876Foa();
        this.U = null;
    }

    @Override // android.view.View
    public final boolean canScrollHorizontally(int i) {
        if (this.R.isEmpty()) {
            return super.canScrollHorizontally(i);
        }
        int size = this.R.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((RH1) this.R.get(i2)).b(i)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public final boolean canScrollVertically(int i) {
        if (this.R.isEmpty()) {
            return super.canScrollVertically(i);
        }
        int size = this.R.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((RH1) this.R.get(i2)).a(i)) {
                return true;
            }
        }
        return false;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            C2876Foa c2876Foa = this.T;
            int width = getWidth();
            int height = getHeight();
            c2876Foa.a = width;
            c2876Foa.b = height;
            ((D85) this.b.get(i)).e(this, canvas, this.T);
        }
        super.onDraw(canvas);
        while (true) {
            size--;
            if (size < 0) {
                return;
            }
            C2876Foa c2876Foa2 = this.T;
            int width2 = getWidth();
            int height2 = getHeight();
            c2876Foa2.a = width2;
            c2876Foa2.b = height2;
            ((D85) this.b.get(size)).a(this, canvas, this.T);
        }
    }

    @Override // android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int size = this.a.size();
        for (int i5 = 0; i5 < size; i5++) {
            ((InterfaceC23431i48) this.a.get(i5)).f(this, z, i, i2, i3, i4);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        int size = this.a.size();
        int iMax = 0;
        int iMax2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            ((InterfaceC23431i48) this.a.get(i3)).c(this, this.S, i, i2);
            iMax = Math.max(iMax, View.resolveSize(this.S.a, i));
            iMax2 = Math.max(iMax2, View.resolveSize(this.S.b, i2));
        }
        setMeasuredDimension(iMax, iMax2);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 0) {
            this.U = null;
        }
        InterfaceC38474uDg interfaceC38474uDg = this.U;
        if (interfaceC38474uDg != null) {
            interfaceC38474uDg.b(this, motionEvent);
            return true;
        }
        boolean z = false;
        for (int size = this.c.size() - 1; size >= 0; size--) {
            InterfaceC38474uDg interfaceC38474uDg2 = (InterfaceC38474uDg) this.c.get(size);
            if (interfaceC38474uDg2.b(this, motionEvent)) {
                this.U = interfaceC38474uDg2;
                int action = motionEvent.getAction();
                motionEvent.setAction(3);
                int size2 = this.c.size();
                while (true) {
                    size2--;
                    if (size2 < 0) {
                        motionEvent.setAction(action);
                        return true;
                    }
                    InterfaceC38474uDg interfaceC38474uDg3 = (InterfaceC38474uDg) this.c.get(size2);
                    if (interfaceC38474uDg3 != interfaceC38474uDg2) {
                        interfaceC38474uDg3.b(this, motionEvent);
                    }
                }
            } else {
                if (!z && interfaceC38474uDg2.g(motionEvent)) {
                    z = true;
                }
            }
        }
        return z;
    }
}
