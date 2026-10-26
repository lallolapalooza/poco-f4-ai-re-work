package com.ss.android.vesdk.faceinfo;

import X.C36340EMk;
import X.C36453EQt;
import X.PVI;
import X.PVJ;
import android.graphics.Rect;
import com.bytedance.covode.number.Covode;
import com.ss.android.medialib.model.FaceDetect;
import com.ss.android.medialib.model.FaceDetectInfo;

/* JADX INFO: loaded from: classes6.dex */
public class VEFaceDetectInfo {
    public int faceCount;
    public PVJ[] info;
    public C36340EMk parcelWrapper;

    static {
        Covode.recordClassIndex(115413);
    }

    public PVJ[] getInfo() {
        return this.info;
    }

    public void readFromParcel() {
        this.faceCount = this.parcelWrapper.LIZ();
        C36453EQt.LIZIZ("VEFaceDetectInfo", "face count = " + this.faceCount);
        this.info = new PVJ[this.faceCount];
        for (int i = 0; i < this.faceCount; i++) {
            PVJ pvj = new PVJ();
            pvj.LJIIL = this.parcelWrapper;
            if (pvj.LJIIL != null) {
                C36340EMk c36340EMk = pvj.LJIIL;
                pvj.LIZ = new Rect(c36340EMk.LIZ(), c36340EMk.LIZ(), c36340EMk.LIZ(), c36340EMk.LIZ());
                pvj.LIZIZ = pvj.LJIIL.LIZIZ();
                pvj.LIZJ = pvj.LJIIL.LIZ(106);
                pvj.LIZLLL = pvj.LJIIL.LIZIZ(106);
                pvj.LJ = pvj.LJIIL.LIZIZ();
                pvj.LJFF = pvj.LJIIL.LIZIZ();
                pvj.LJI = pvj.LJIIL.LIZIZ();
                pvj.LJII = pvj.LJIIL.LIZIZ();
                pvj.LJIIIIZZ = pvj.LJIIL.LIZ();
                pvj.LJIIIZ = pvj.LJIIL.LIZ();
                pvj.LJIIJ = pvj.LJIIL.LIZ();
            }
            this.info[i] = pvj;
        }
        int iLIZ = this.parcelWrapper.LIZ();
        C36453EQt.LIZIZ("VEFaceDetectInfo", "faceExtFlag = ".concat(String.valueOf(iLIZ)));
        if (iLIZ == 0) {
            return;
        }
        for (int i2 = 0; i2 < this.faceCount; i2++) {
            PVI pvi = new PVI();
            pvi.LJIIJJI = this.parcelWrapper;
            pvi.LJIIL = iLIZ;
            pvi.LIZ();
            this.info[i2].LJIIJJI = pvi;
        }
    }

    public void setInfo(PVJ[] pvjArr) {
        this.info = pvjArr;
    }

    public void setParcelWrapper(C36340EMk c36340EMk) {
        this.parcelWrapper = c36340EMk;
    }

    public static VEFaceDetectInfo covert(FaceDetectInfo faceDetectInfo) {
        if (faceDetectInfo == null || faceDetectInfo.getInfo() == null) {
            return null;
        }
        VEFaceDetectInfo vEFaceDetectInfo = new VEFaceDetectInfo();
        PVJ[] pvjArr = new PVJ[faceDetectInfo.getInfo().length];
        FaceDetect[] info = faceDetectInfo.getInfo();
        int length = info.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            FaceDetect faceDetect = info[i];
            PVJ pvj = new PVJ();
            pvj.LIZ = faceDetect.getRect();
            pvj.LIZJ = faceDetect.getPoints();
            pvj.LJIIIZ = faceDetect.getAction();
            pvj.LJII = faceDetect.getEyeDistance();
            if (faceDetect.getFaceExtInfo() != null) {
                PVI pvi = new PVI();
                pvi.LIZIZ = faceDetect.getFaceExtInfo().eyebrowCount;
                pvi.LJI = faceDetect.getFaceExtInfo().eyeBrowLeftPoints;
                pvi.LJII = faceDetect.getFaceExtInfo().eyeBrowRightPoints;
                pvi.LIZ = faceDetect.getFaceExtInfo().eyeCount;
                pvi.LJ = faceDetect.getFaceExtInfo().eyeLeftPoints;
                pvi.LJFF = faceDetect.getFaceExtInfo().eyeRightPoints;
                pvi.LIZJ = faceDetect.getFaceExtInfo().lipCount;
                pvi.LJIIIZ = faceDetect.getFaceExtInfo().irisLeftPoints;
                pvi.LJIIJ = faceDetect.getFaceExtInfo().irisRightPoints;
                pvi.LIZLLL = faceDetect.getFaceExtInfo().irisCount;
                pvj.LJIIJJI = pvi;
            }
            pvj.LJIIIIZZ = faceDetect.getFaceID();
            pvj.LJFF = faceDetect.getPitch();
            pvj.LIZLLL = faceDetect.getPointVisibility();
            pvj.LJI = faceDetect.getRoll();
            pvj.LJ = faceDetect.getYaw();
            pvj.LIZIZ = faceDetect.getScore();
            pvj.LJIIJ = faceDetect.getTrackCount();
            pvjArr[i2] = pvj;
            i++;
            i2++;
        }
        vEFaceDetectInfo.setInfo(pvjArr);
        return vEFaceDetectInfo;
    }
}
