package com.xiaomi.aiautobrt;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_securitycoreadd/sources/framework/miui-framework.dex */
public class IndividualTrainEvent implements Parcelable {
    public static final Parcelable.Creator<IndividualTrainEvent> CREATOR = new Parcelable.Creator<IndividualTrainEvent>() { // from class: com.xiaomi.aiautobrt.IndividualTrainEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IndividualTrainEvent createFromParcel(Parcel in) {
            return new IndividualTrainEvent(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IndividualTrainEvent[] newArray(int size) {
            return new IndividualTrainEvent[size];
        }
    };
    private float mCurModelMAE;
    private float mCurModelMAPE;
    private float mModelTrainLoss;
    private float mPreModelMAE;
    private float mPreModelMAPE;
    private int mTrainSampleNum;

    public IndividualTrainEvent() {
        this.mTrainSampleNum = -1;
        this.mPreModelMAE = -1.0f;
        this.mCurModelMAE = -1.0f;
        this.mPreModelMAPE = -1.0f;
        this.mCurModelMAPE = -1.0f;
        this.mModelTrainLoss = -1.0f;
    }

    public int getTrainSampleNum() {
        return this.mTrainSampleNum;
    }

    public float getPreModelMAE() {
        return this.mPreModelMAE;
    }

    public float getCurModelMAE() {
        return this.mCurModelMAE;
    }

    public float getPreModelMAPE() {
        return this.mPreModelMAPE;
    }

    public float getCurModelMAPE() {
        return this.mCurModelMAPE;
    }

    public float getModelTrainLoss() {
        return this.mModelTrainLoss;
    }

    public void setTrainSampleNum(int trainSampleNum) {
        this.mTrainSampleNum = trainSampleNum;
    }

    public void setPreModelMAE(float preModelMAE) {
        this.mPreModelMAE = preModelMAE;
    }

    public void setCurModelMAE(float curModelMAE) {
        this.mCurModelMAE = curModelMAE;
    }

    public void setPreModelMAPE(float preModelMAPE) {
        this.mPreModelMAPE = preModelMAPE;
    }

    public void setCurModelMAPE(float curModelMAPE) {
        this.mCurModelMAPE = curModelMAPE;
    }

    public void setModelTrainLoss(float modelTrainLoss) {
        this.mModelTrainLoss = modelTrainLoss;
    }

    protected IndividualTrainEvent(Parcel in) {
        this.mTrainSampleNum = -1;
        this.mPreModelMAE = -1.0f;
        this.mCurModelMAE = -1.0f;
        this.mPreModelMAPE = -1.0f;
        this.mCurModelMAPE = -1.0f;
        this.mModelTrainLoss = -1.0f;
        this.mTrainSampleNum = in.readInt();
        this.mPreModelMAE = in.readFloat();
        this.mCurModelMAE = in.readFloat();
        this.mPreModelMAPE = in.readFloat();
        this.mCurModelMAPE = in.readFloat();
        this.mModelTrainLoss = in.readFloat();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mTrainSampleNum);
        dest.writeFloat(this.mPreModelMAE);
        dest.writeFloat(this.mCurModelMAE);
        dest.writeFloat(this.mPreModelMAPE);
        dest.writeFloat(this.mCurModelMAPE);
        dest.writeFloat(this.mModelTrainLoss);
    }
}
