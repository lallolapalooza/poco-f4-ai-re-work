package X;

import android.graphics.PointF;
import com.bytedance.covode.number.Covode;

/* JADX INFO: renamed from: X.PZb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes2.dex */
public class C64697PZb {
    public int LIZ;
    public int LIZIZ;
    public int LIZJ;
    public int LIZLLL;
    public PointF[] LJ;
    public PointF[] LJFF;
    public PointF[] LJI;
    public PointF[] LJII;
    public PointF[] LJIIIIZZ;
    public PointF[] LJIIIZ;
    public PointF[] LJIIJ;
    public C34719DjF LJIIJJI;
    public int LJIIL;

    static {
        Covode.recordClassIndex(115422);
    }

    public final void LIZ() {
        C34719DjF c34719DjF = this.LJIIJJI;
        if (c34719DjF == null) {
            return;
        }
        if ((this.LJIIL & 1) != 0) {
            this.LIZ = c34719DjF.LIZ();
            this.LJ = this.LJIIJJI.LIZ(22);
            this.LJFF = this.LJIIJJI.LIZ(22);
            C7Q2.LIZIZ("FaceExtInfo", "first left eye point = " + this.LJ[0].x + " X " + this.LJ[0].y);
        }
        if ((this.LJIIL & 2) != 0) {
            this.LIZIZ = this.LJIIJJI.LIZ();
            this.LJI = this.LJIIJJI.LIZ(13);
            this.LJII = this.LJIIJJI.LIZ(13);
            C7Q2.LIZIZ("FaceExtInfo", "first left eyebrow point = " + this.LJI[0].x + " X " + this.LJI[0].y);
        }
        if ((this.LJIIL & 4) != 0) {
            this.LIZJ = this.LJIIJJI.LIZ();
            this.LJIIIIZZ = this.LJIIJJI.LIZ(64);
            C7Q2.LIZIZ("FaceExtInfo", "first lip point = " + this.LJIIIIZZ[0].x + " X " + this.LJIIIIZZ[0].y);
        }
        if ((this.LJIIL & 8) != 0) {
            this.LIZLLL = this.LJIIJJI.LIZ();
            this.LJIIIZ = this.LJIIJJI.LIZ(20);
            this.LJIIJ = this.LJIIJJI.LIZ(20);
            C7Q2.LIZIZ("FaceExtInfo", "first left iris point = " + this.LJIIIZ[0].x + " X " + this.LJIIIZ[0].y);
        }
    }
}
