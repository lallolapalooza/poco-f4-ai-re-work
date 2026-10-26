package com.ss.android.vesdk.faceinfo;

import X.C34719DjF;
import com.bytedance.covode.number.Covode;

/* JADX INFO: loaded from: classes6.dex */
public class VEFaceAttribute {
    public float age;
    public float angryScore;
    public float arousal;
    public float attractive;
    public float blurScore;
    public float boyProb;
    public float[] expProbs;
    public int expType;
    public float happyScore;
    public float illumination;
    public float lipstickProb;
    public float maskProb;
    public float mustacheProb;
    public C34719DjF parcelWrapper;
    public float quality;
    public float realFaceProb;
    public float sadScore;
    public float surpriseScore;
    public float valence;
    public float wearGlassProb;
    public float wearHatProb;
    public float wearSunglassProb;

    static {
        Covode.recordClassIndex(115415);
    }

    public static int getExpressionNum() {
        return 7;
    }

    public float getAge() {
        return this.age;
    }

    public float getAngryScore() {
        return this.angryScore;
    }

    public float getArousal() {
        return this.arousal;
    }

    public float getAttractive() {
        return this.attractive;
    }

    public float getBlurScore() {
        return this.blurScore;
    }

    public float getBoyProb() {
        return this.boyProb;
    }

    public float[] getExpProbs() {
        return this.expProbs;
    }

    public int getExpType() {
        return this.expType;
    }

    public float getHappyScore() {
        return this.happyScore;
    }

    public float getIllumination() {
        return this.illumination;
    }

    public float getLipstickProb() {
        return this.lipstickProb;
    }

    public float getMaskProb() {
        return this.maskProb;
    }

    public float getMustacheProb() {
        return this.mustacheProb;
    }

    public float getQuality() {
        return this.quality;
    }

    public float getRealFaceProb() {
        return this.realFaceProb;
    }

    public float getSadScore() {
        return this.sadScore;
    }

    public float getSurpriseScore() {
        return this.surpriseScore;
    }

    public float getValence() {
        return this.valence;
    }

    public float getWearGlassProb() {
        return this.wearGlassProb;
    }

    public float getWearHatProb() {
        return this.wearHatProb;
    }

    public float getWearSunglassProb() {
        return this.wearSunglassProb;
    }

    public void readFromParcel() {
        C34719DjF c34719DjF = this.parcelWrapper;
        if (c34719DjF == null) {
            return;
        }
        this.age = c34719DjF.LIZIZ();
        this.boyProb = this.parcelWrapper.LIZIZ();
        this.attractive = this.parcelWrapper.LIZIZ();
        this.happyScore = this.parcelWrapper.LIZIZ();
        this.expType = this.parcelWrapper.LIZ();
        this.expProbs = this.parcelWrapper.LIZIZ(7);
        this.realFaceProb = this.parcelWrapper.LIZIZ();
        this.quality = this.parcelWrapper.LIZIZ();
        this.arousal = this.parcelWrapper.LIZIZ();
        this.valence = this.parcelWrapper.LIZIZ();
        this.sadScore = this.parcelWrapper.LIZIZ();
        this.angryScore = this.parcelWrapper.LIZIZ();
        this.surpriseScore = this.parcelWrapper.LIZIZ();
        this.maskProb = this.parcelWrapper.LIZIZ();
        this.wearHatProb = this.parcelWrapper.LIZIZ();
        this.mustacheProb = this.parcelWrapper.LIZIZ();
        this.lipstickProb = this.parcelWrapper.LIZIZ();
        this.wearGlassProb = this.parcelWrapper.LIZIZ();
        this.wearSunglassProb = this.parcelWrapper.LIZIZ();
        this.blurScore = this.parcelWrapper.LIZIZ();
        this.illumination = this.parcelWrapper.LIZIZ();
    }

    public void setAge(float f) {
        this.age = f;
    }

    public void setAngryScore(float f) {
        this.angryScore = f;
    }

    public void setArousal(float f) {
        this.arousal = f;
    }

    public void setAttractive(float f) {
        this.attractive = f;
    }

    public void setBlurScore(float f) {
        this.blurScore = f;
    }

    public void setBoyProb(float f) {
        this.boyProb = f;
    }

    public void setExpProbs(float[] fArr) {
        this.expProbs = fArr;
    }

    public void setExpType(int i) {
        this.expType = i;
    }

    public void setHappyScore(float f) {
        this.happyScore = f;
    }

    public void setIllumination(float f) {
        this.illumination = f;
    }

    public void setLipstickProb(float f) {
        this.lipstickProb = f;
    }

    public void setMaskProb(float f) {
        this.maskProb = f;
    }

    public void setMustacheProb(float f) {
        this.mustacheProb = f;
    }

    public void setParcelWrapper(C34719DjF c34719DjF) {
        this.parcelWrapper = c34719DjF;
    }

    public void setQuality(float f) {
        this.quality = f;
    }

    public void setRealFaceProb(float f) {
        this.realFaceProb = f;
    }

    public void setSadScore(float f) {
        this.sadScore = f;
    }

    public void setSurpriseScore(float f) {
        this.surpriseScore = f;
    }

    public void setValence(float f) {
        this.valence = f;
    }

    public void setWearGlassProb(float f) {
        this.wearGlassProb = f;
    }

    public void setWearHatProb(float f) {
        this.wearHatProb = f;
    }

    public void setWearSunglassProb(float f) {
        this.wearSunglassProb = f;
    }
}
