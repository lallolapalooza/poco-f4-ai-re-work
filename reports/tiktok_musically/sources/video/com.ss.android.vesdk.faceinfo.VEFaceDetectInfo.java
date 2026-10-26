package com.ss.android.vesdk.faceinfo;

import X.C34719DjF;
import X.C64697PZb;
import X.C64698PZc;
import X.C7Q2;
import android.graphics.Rect;
import com.bytedance.covode.number.Covode;
import com.ss.android.medialib.model.FaceDetect;
import com.ss.android.medialib.model.FaceDetectInfo;

/* JADX INFO: loaded from: classes6.dex */
public class VEFaceDetectInfo {
    public int faceCount;
    public C64698PZc[] info;
    public C34719DjF parcelWrapper;

    static {
        Covode.recordClassIndex(115417);
    }

    public C64698PZc[] getInfo() {
        return this.info;
    }

    public void readFromParcel() {
        this.faceCount = this.parcelWrapper.LIZ();
        C7Q2.LIZIZ("VEFaceDetectInfo", "face count = " + this.faceCount);
        this.info = new C64698PZc[this.faceCount];
        for (int i = 0; i < this.faceCount; i++) {
            C64698PZc c64698PZc = new C64698PZc();
            c64698PZc.LJIIL = this.parcelWrapper;
            if (c64698PZc.LJIIL != null) {
                C34719DjF c34719DjF = c64698PZc.LJIIL;
                c64698PZc.LIZ = new Rect(c34719DjF.LIZ(), c34719DjF.LIZ(), c34719DjF.LIZ(), c34719DjF.LIZ());
                c64698PZc.LIZIZ = c64698PZc.LJIIL.LIZIZ();
                c64698PZc.LIZJ = c64698PZc.LJIIL.LIZ(106);
                c64698PZc.LIZLLL = c64698PZc.LJIIL.LIZIZ(106);
                c64698PZc.LJ = c64698PZc.LJIIL.LIZIZ();
                c64698PZc.LJFF = c64698PZc.LJIIL.LIZIZ();
                c64698PZc.LJI = c64698PZc.LJIIL.LIZIZ();
                c64698PZc.LJII = c64698PZc.LJIIL.LIZIZ();
                c64698PZc.LJIIIIZZ = c64698PZc.LJIIL.LIZ();
                c64698PZc.LJIIIZ = c64698PZc.LJIIL.LIZ();
                c64698PZc.LJIIJ = c64698PZc.LJIIL.LIZ();
            }
            this.info[i] = c64698PZc;
        }
        int iLIZ = this.parcelWrapper.LIZ();
        C7Q2.LIZIZ("VEFaceDetectInfo", "faceExtFlag = ".concat(String.valueOf(iLIZ)));
        if (iLIZ == 0) {
            return;
        }
        for (int i2 = 0; i2 < this.faceCount; i2++) {
            C64697PZb c64697PZb = new C64697PZb();
            c64697PZb.LJIIJJI = this.parcelWrapper;
            c64697PZb.LJIIL = iLIZ;
            c64697PZb.LIZ();
            this.info[i2].LJIIJJI = c64697PZb;
        }
    }

    public void setInfo(C64698PZc[] c64698PZcArr) {
        this.info = c64698PZcArr;
    }

    public void setParcelWrapper(C34719DjF c34719DjF) {
        this.parcelWrapper = c34719DjF;
    }

    public static VEFaceDetectInfo covert(FaceDetectInfo faceDetectInfo) {
        if (faceDetectInfo == null || faceDetectInfo.getInfo() == null) {
            return null;
        }
        VEFaceDetectInfo vEFaceDetectInfo = new VEFaceDetectInfo();
        C64698PZc[] c64698PZcArr = new C64698PZc[faceDetectInfo.getInfo().length];
        FaceDetect[] info = faceDetectInfo.getInfo();
        int length = info.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            FaceDetect faceDetect = info[i];
            C64698PZc c64698PZc = new C64698PZc();
            c64698PZc.LIZ = faceDetect.getRect();
            c64698PZc.LIZJ = faceDetect.getPoints();
            c64698PZc.LJIIIZ = faceDetect.getAction();
            c64698PZc.LJII = faceDetect.getEyeDistance();
            if (faceDetect.getFaceExtInfo() != null) {
                C64697PZb c64697PZb = new C64697PZb();
                c64697PZb.LIZIZ = faceDetect.getFaceExtInfo().eyebrowCount;
                c64697PZb.LJI = faceDetect.getFaceExtInfo().eyeBrowLeftPoints;
                c64697PZb.LJII = faceDetect.getFaceExtInfo().eyeBrowRightPoints;
                c64697PZb.LIZ = faceDetect.getFaceExtInfo().eyeCount;
                c64697PZb.LJ = faceDetect.getFaceExtInfo().eyeLeftPoints;
                c64697PZb.LJFF = faceDetect.getFaceExtInfo().eyeRightPoints;
                c64697PZb.LIZJ = faceDetect.getFaceExtInfo().lipCount;
                c64697PZb.LJIIIZ = faceDetect.getFaceExtInfo().irisLeftPoints;
                c64697PZb.LJIIJ = faceDetect.getFaceExtInfo().irisRightPoints;
                c64697PZb.LIZLLL = faceDetect.getFaceExtInfo().irisCount;
                c64698PZc.LJIIJJI = c64697PZb;
            }
            c64698PZc.LJIIIIZZ = faceDetect.getFaceID();
            c64698PZc.LJFF = faceDetect.getPitch();
            c64698PZc.LIZLLL = faceDetect.getPointVisibility();
            c64698PZc.LJI = faceDetect.getRoll();
            c64698PZc.LJ = faceDetect.getYaw();
            c64698PZc.LIZIZ = faceDetect.getScore();
            c64698PZc.LJIIJ = faceDetect.getTrackCount();
            c64698PZcArr[i2] = c64698PZc;
            i++;
            i2++;
        }
        vEFaceDetectInfo.setInfo(c64698PZcArr);
        return vEFaceDetectInfo;
    }
}
