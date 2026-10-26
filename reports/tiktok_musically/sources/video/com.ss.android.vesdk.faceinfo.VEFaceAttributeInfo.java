package com.ss.android.vesdk.faceinfo;

import X.C34719DjF;
import X.C7Q2;
import com.bytedance.covode.number.Covode;
import com.ss.android.medialib.model.FaceAttribute;
import com.ss.android.medialib.model.FaceAttributeInfo;

/* JADX INFO: loaded from: classes6.dex */
public class VEFaceAttributeInfo {
    public int faceCount;
    public VEFaceAttribute[] info;
    public C34719DjF parcelWrapper;

    static {
        Covode.recordClassIndex(115416);
    }

    public VEFaceAttribute[] getInfo() {
        return this.info;
    }

    public void readFromParcel() {
        C34719DjF c34719DjF = this.parcelWrapper;
        if (c34719DjF == null) {
            return;
        }
        this.faceCount = c34719DjF.LIZ();
        C7Q2.LIZIZ("VEFaceAttributeInfo", "face count = " + this.faceCount);
        int i = this.faceCount;
        if (i == 0) {
            return;
        }
        this.info = new VEFaceAttribute[i];
        for (int i2 = 0; i2 < this.faceCount; i2++) {
            VEFaceAttribute vEFaceAttribute = new VEFaceAttribute();
            vEFaceAttribute.setParcelWrapper(this.parcelWrapper);
            vEFaceAttribute.readFromParcel();
            this.info[i2] = vEFaceAttribute;
        }
    }

    public void setInfo(VEFaceAttribute[] vEFaceAttributeArr) {
        this.info = vEFaceAttributeArr;
    }

    public void setParcelWrapper(C34719DjF c34719DjF) {
        this.parcelWrapper = c34719DjF;
    }

    public static VEFaceAttributeInfo convert(FaceAttributeInfo faceAttributeInfo) {
        if (faceAttributeInfo == null) {
            return null;
        }
        VEFaceAttributeInfo vEFaceAttributeInfo = new VEFaceAttributeInfo();
        FaceAttribute[] info = faceAttributeInfo.getInfo();
        int i = 0;
        if (info == null) {
            vEFaceAttributeInfo.setInfo(new VEFaceAttribute[0]);
        } else {
            VEFaceAttribute[] vEFaceAttributeArr = new VEFaceAttribute[info.length];
            vEFaceAttributeInfo.setInfo(vEFaceAttributeArr);
            int length = info.length;
            int i2 = 0;
            while (i < length) {
                FaceAttribute faceAttribute = info[i];
                VEFaceAttribute vEFaceAttribute = new VEFaceAttribute();
                int i3 = i2 + 1;
                vEFaceAttributeArr[i2] = vEFaceAttribute;
                if (faceAttribute != null) {
                    vEFaceAttribute.setAge(faceAttribute.getAge());
                    vEFaceAttribute.setAngryScore(faceAttribute.getAngryScore());
                    vEFaceAttribute.setArousal(faceAttribute.getArousal());
                    vEFaceAttribute.setAngryScore(faceAttribute.getAngryScore());
                    vEFaceAttribute.setAttractive(faceAttribute.getAttractive());
                    vEFaceAttribute.setBlurScore(faceAttribute.getBlurScore());
                    vEFaceAttribute.setBoyProb(faceAttribute.getBoyProb());
                    vEFaceAttribute.setExpProbs(faceAttribute.getExpProbs());
                    vEFaceAttribute.setExpType(faceAttribute.getExpType());
                    vEFaceAttribute.setHappyScore(faceAttribute.getHappyScore());
                    vEFaceAttribute.setIllumination(faceAttribute.getIllumination());
                    vEFaceAttribute.setLipstickProb(faceAttribute.getLipstickProb());
                    vEFaceAttribute.setMaskProb(faceAttribute.getMaskProb());
                    vEFaceAttribute.setMustacheProb(faceAttribute.getMustacheProb());
                    vEFaceAttribute.setQuality(faceAttribute.getQuality());
                    vEFaceAttribute.setRealFaceProb(faceAttribute.getRealFaceProb());
                    vEFaceAttribute.setSadScore(faceAttribute.getSadScore());
                    vEFaceAttribute.setSurpriseScore(faceAttribute.getSurpriseScore());
                    vEFaceAttribute.setValence(faceAttribute.getValence());
                    vEFaceAttribute.setWearGlassProb(faceAttribute.getWearGlassProb());
                    vEFaceAttribute.setWearHatProb(faceAttribute.getWearHatProb());
                    vEFaceAttribute.setWearSunglassProb(faceAttribute.getWearSunglassProb());
                }
                i++;
                i2 = i3;
            }
        }
        return vEFaceAttributeInfo;
    }
}
