package com.xiaomi.aiautobrt;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Slog;

/* JADX INFO: loaded from: /run/media/asdf/db69437b-ed35-414a-8bd1-bade16b06830/re_work/reports/miui_securitycoreadd/sources/framework/miui-framework.dex */
public class IndividualModelEvent implements Parcelable {
    public static final Parcelable.Creator<IndividualModelEvent> CREATOR = new Parcelable.Creator<IndividualModelEvent>() { // from class: com.xiaomi.aiautobrt.IndividualModelEvent.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IndividualModelEvent createFromParcel(Parcel in) {
            return new IndividualModelEvent(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public IndividualModelEvent[] newArray(int size) {
            return new IndividualModelEvent[size];
        }
    };
    private static final String TAG = "IndividualModelEvent";
    private float mAmbientLux;
    private float mAmbientLuxSpan;
    private float mAppCategoryId;
    private float mCurrentBrightness;
    private float mCurrentBrightnessSpan;
    private float mDefaultConfigBrightness;
    private float mMixedOrientationApp;
    private float mOrientation;
    private long mTimeStamp;

    public IndividualModelEvent() {
    }

    public IndividualModelEvent(float brightness, float brightnessSpan, float ambientLux, float ambientLuxSpan, float curveBrightness, float categoryId, float orientation, float mixedOrientationApp, long timeStamp) {
        this.mCurrentBrightness = brightness;
        this.mCurrentBrightnessSpan = brightnessSpan;
        this.mAmbientLux = ambientLux;
        this.mAmbientLuxSpan = ambientLuxSpan;
        this.mDefaultConfigBrightness = curveBrightness;
        this.mAppCategoryId = categoryId;
        this.mOrientation = orientation;
        this.mMixedOrientationApp = mixedOrientationApp;
        this.mTimeStamp = timeStamp;
    }

    protected IndividualModelEvent(Parcel in) {
        this.mCurrentBrightness = in.readFloat();
        this.mCurrentBrightnessSpan = in.readFloat();
        this.mAmbientLux = in.readFloat();
        this.mAmbientLuxSpan = in.readFloat();
        this.mDefaultConfigBrightness = in.readFloat();
        this.mAppCategoryId = in.readFloat();
        this.mOrientation = in.readFloat();
        this.mMixedOrientationApp = in.readFloat();
        this.mTimeStamp = in.readLong();
    }

    public float getCurrentBrightness() {
        return this.mCurrentBrightness;
    }

    public float getCurrentBrightnessSpan() {
        return this.mCurrentBrightnessSpan;
    }

    public float getAmbientLux() {
        return this.mAmbientLux;
    }

    public float getAmbientLuxSpan() {
        return this.mAmbientLuxSpan;
    }

    public float getDefaultConfigBrightness() {
        return this.mDefaultConfigBrightness;
    }

    public float getAppCategoryId() {
        return this.mAppCategoryId;
    }

    public float getOrientation() {
        return this.mOrientation;
    }

    public float getMixedOrientationApp() {
        return this.mMixedOrientationApp;
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    public boolean isValidEvent() {
        if (!Float.isNaN(this.mCurrentBrightness)) {
            float f = this.mCurrentBrightness;
            if (f >= 0.0f && f <= 1.0f) {
                if (!Float.isNaN(this.mCurrentBrightnessSpan)) {
                    float f2 = this.mCurrentBrightnessSpan;
                    if (f2 >= 0.0f && f2 <= 1.0f) {
                        if (!Float.isNaN(this.mAmbientLux)) {
                            float f3 = this.mAmbientLux;
                            if (f3 >= 0.0f && f3 <= 1.0f) {
                                if (!Float.isNaN(this.mAmbientLuxSpan)) {
                                    float f4 = this.mAmbientLuxSpan;
                                    if (f4 >= 0.0f && f4 <= 1.0f) {
                                        if (!Float.isNaN(this.mDefaultConfigBrightness)) {
                                            float f5 = this.mDefaultConfigBrightness;
                                            if (f5 >= 0.0f && f5 <= 1.0f) {
                                                if (!Float.isNaN(this.mOrientation)) {
                                                    float f6 = this.mOrientation;
                                                    if (f6 >= 0.0f && f6 <= 1.0f) {
                                                        if (!Float.isNaN(this.mAppCategoryId)) {
                                                            float f7 = this.mAppCategoryId;
                                                            if (f7 >= 0.0f && f7 <= 1.0f) {
                                                                if (!Float.isNaN(this.mMixedOrientationApp)) {
                                                                    float f8 = this.mMixedOrientationApp;
                                                                    if (f8 >= 0.0f && f8 <= 1.0f) {
                                                                        return true;
                                                                    }
                                                                }
                                                                Slog.w(TAG, "Event is invalid due to crossed orient-app is invalid.");
                                                                return false;
                                                            }
                                                        }
                                                        Slog.w(TAG, "Event is invalid due to app id is invalid.");
                                                        return false;
                                                    }
                                                }
                                                Slog.w(TAG, "Event is invalid due to orient is invalid.");
                                                return false;
                                            }
                                        }
                                        Slog.w(TAG, "Event is invalid due to def config brt is invalid.");
                                        return false;
                                    }
                                }
                                Slog.w(TAG, "Event is invalid due to lux span is invalid.");
                                return false;
                            }
                        }
                        Slog.w(TAG, "Event is invalid due to lux is invalid.");
                        return false;
                    }
                }
                Slog.w(TAG, "Event is invalid due to lux is invalid.");
                return false;
            }
        }
        Slog.w(TAG, "Event is invalid due to current brt is invalid.");
        return false;
    }

    public boolean isValidRawEvent() {
        if (Float.isNaN(this.mCurrentBrightness)) {
            Slog.w(TAG, "Event is invalid due to current brt is invalid.");
            return false;
        }
        if (Float.isNaN(this.mCurrentBrightnessSpan)) {
            Slog.w(TAG, "Event is invalid due to lux is invalid.");
            return false;
        }
        if (Float.isNaN(this.mAmbientLux)) {
            Slog.w(TAG, "Event is invalid due to lux is invalid.");
            return false;
        }
        if (Float.isNaN(this.mAmbientLuxSpan)) {
            Slog.w(TAG, "Event is invalid due to lux span is invalid.");
            return false;
        }
        if (Float.isNaN(this.mDefaultConfigBrightness)) {
            Slog.w(TAG, "Event is invalid due to def config brt is invalid.");
            return false;
        }
        if (Float.isNaN(this.mOrientation)) {
            Slog.w(TAG, "Event is invalid due to orient is invalid.");
            return false;
        }
        if (Float.isNaN(this.mAppCategoryId)) {
            Slog.w(TAG, "Event is invalid due to app id is invalid.");
            return false;
        }
        if (Float.isNaN(this.mMixedOrientationApp)) {
            Slog.w(TAG, "Event is invalid due to crossed orient-app is invalid.");
            return false;
        }
        return true;
    }

    public String toString() {
        return "IndividualModelEvent{curBrt: " + this.mCurrentBrightness + ", curBrtSpan: " + this.mCurrentBrightnessSpan + ", lux: " + this.mAmbientLux + ", luxSpan: " + this.mAmbientLuxSpan + ", defaultConfigBrt: " + this.mDefaultConfigBrightness + ", appCateId: " + this.mAppCategoryId + ", orientation: " + this.mOrientation + ", timeStamp: " + this.mTimeStamp + '}';
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.mCurrentBrightness);
        dest.writeFloat(this.mCurrentBrightnessSpan);
        dest.writeFloat(this.mAmbientLux);
        dest.writeFloat(this.mAmbientLuxSpan);
        dest.writeFloat(this.mDefaultConfigBrightness);
        dest.writeFloat(this.mAppCategoryId);
        dest.writeFloat(this.mOrientation);
        dest.writeFloat(this.mMixedOrientationApp);
        dest.writeLong(this.mTimeStamp);
    }

    public static final class Builder {
        private float ambientLux;
        private float ambientLuxSpan;
        private float appCategoryId;
        private float currentBrightness;
        private float currentBrightnessSpan;
        private float defaultConfigBrightness;
        private float mixedOrientationAppId;
        private float orientation;
        private long timeStamp;

        public Builder setNormalizedCurrentBrightness(float brightness) {
            this.currentBrightness = brightness;
            return this;
        }

        public Builder setNormalizedCurrentBrightnessSpan(float brightnessSpan) {
            this.currentBrightnessSpan = brightnessSpan;
            return this;
        }

        public Builder setNormalizedAmbientLux(float ambientLux) {
            this.ambientLux = ambientLux;
            return this;
        }

        public Builder setNormalizedAmbientLuxSpan(float luxSpan) {
            this.ambientLuxSpan = luxSpan;
            return this;
        }

        public Builder setNormalizedDefaultConfigBrightness(float configBrightness) {
            this.defaultConfigBrightness = configBrightness;
            return this;
        }

        public Builder setCurrentBrightness(float brightness) {
            this.currentBrightness = brightness;
            return this;
        }

        public Builder setCurrentBrightnessSpan(float brightnessSpan) {
            this.currentBrightnessSpan = brightnessSpan;
            return this;
        }

        public Builder setAmbientLux(float ambientLux) {
            this.ambientLux = ambientLux;
            return this;
        }

        public Builder setAmbientLuxSpan(float luxSpan) {
            this.ambientLuxSpan = luxSpan;
            return this;
        }

        public Builder setDefaultConfigBrightness(float configBrightness) {
            this.defaultConfigBrightness = configBrightness;
            return this;
        }

        public Builder setOrientation(float orientation) {
            this.orientation = orientation;
            return this;
        }

        public Builder setAppCategoryId(float id) {
            this.appCategoryId = id;
            return this;
        }

        public Builder setTimeStamp(long timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public Builder setMixedOrientationAppId(float mixed) {
            this.mixedOrientationAppId = mixed;
            return this;
        }

        public IndividualModelEvent build() {
            return new IndividualModelEvent(this.currentBrightness, this.currentBrightnessSpan, this.ambientLux, this.ambientLuxSpan, this.defaultConfigBrightness, this.appCategoryId, this.orientation, this.mixedOrientationAppId, this.timeStamp);
        }
    }
}
