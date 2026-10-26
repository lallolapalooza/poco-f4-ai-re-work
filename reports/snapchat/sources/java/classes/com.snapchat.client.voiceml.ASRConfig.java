package com.snapchat.client.voiceml;

import defpackage.AbstractC19915fE3;
import defpackage.E;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes6.dex */
public final class ASRConfig {
    public final String mAsrModelName;
    public final AsrMode mAsrType;
    public final int mAudioChannelCount;
    public final BaseASRConfig mBaseConfig;
    public final String mCustomRoute;
    public final ArrayList<IntentsModel> mIntentsModels;
    public final ArrayList<KeywordsModel> mKeywordsModels;
    public final String mLensId;
    public final int mMaxAlternatives;
    public final String mNlpModelName;
    public final boolean mShouldReturnAsrResults;
    public final boolean mShouldReturnInterimAsrResults;
    public final boolean mShouldReturnWordTimeOffsets;
    public final boolean mShouldUseAutomaticPunctuations;
    public final boolean mShouldUseSeperateRecognitionPerChannel;
    public final ArrayList<SpeechContext> mSpeechContexts;

    public ASRConfig(BaseASRConfig baseASRConfig, boolean z, boolean z2, AsrMode asrMode, String str, String str2, int i, boolean z3, boolean z4, boolean z5, int i2, ArrayList<KeywordsModel> arrayList, ArrayList<IntentsModel> arrayList2, String str3, String str4, ArrayList<SpeechContext> arrayList3) {
        this.mBaseConfig = baseASRConfig;
        this.mShouldReturnAsrResults = z;
        this.mShouldReturnInterimAsrResults = z2;
        this.mAsrType = asrMode;
        this.mNlpModelName = str;
        this.mLensId = str2;
        this.mMaxAlternatives = i;
        this.mShouldReturnWordTimeOffsets = z3;
        this.mShouldUseAutomaticPunctuations = z4;
        this.mShouldUseSeperateRecognitionPerChannel = z5;
        this.mAudioChannelCount = i2;
        this.mKeywordsModels = arrayList;
        this.mIntentsModels = arrayList2;
        this.mAsrModelName = str3;
        this.mCustomRoute = str4;
        this.mSpeechContexts = arrayList3;
    }

    public String getAsrModelName() {
        return this.mAsrModelName;
    }

    public AsrMode getAsrType() {
        return this.mAsrType;
    }

    public int getAudioChannelCount() {
        return this.mAudioChannelCount;
    }

    public BaseASRConfig getBaseConfig() {
        return this.mBaseConfig;
    }

    public String getCustomRoute() {
        return this.mCustomRoute;
    }

    public ArrayList<IntentsModel> getIntentsModels() {
        return this.mIntentsModels;
    }

    public ArrayList<KeywordsModel> getKeywordsModels() {
        return this.mKeywordsModels;
    }

    public String getLensId() {
        return this.mLensId;
    }

    public int getMaxAlternatives() {
        return this.mMaxAlternatives;
    }

    public String getNlpModelName() {
        return this.mNlpModelName;
    }

    public boolean getShouldReturnAsrResults() {
        return this.mShouldReturnAsrResults;
    }

    public boolean getShouldReturnInterimAsrResults() {
        return this.mShouldReturnInterimAsrResults;
    }

    public boolean getShouldReturnWordTimeOffsets() {
        return this.mShouldReturnWordTimeOffsets;
    }

    public boolean getShouldUseAutomaticPunctuations() {
        return this.mShouldUseAutomaticPunctuations;
    }

    public boolean getShouldUseSeperateRecognitionPerChannel() {
        return this.mShouldUseSeperateRecognitionPerChannel;
    }

    public ArrayList<SpeechContext> getSpeechContexts() {
        return this.mSpeechContexts;
    }

    public String toString() {
        StringBuilder sbD = AbstractC19915fE3.d("ASRConfig{mBaseConfig=");
        sbD.append(this.mBaseConfig);
        sbD.append(",mShouldReturnAsrResults=");
        sbD.append(this.mShouldReturnAsrResults);
        sbD.append(",mShouldReturnInterimAsrResults=");
        sbD.append(this.mShouldReturnInterimAsrResults);
        sbD.append(",mAsrType=");
        sbD.append(this.mAsrType);
        sbD.append(",mNlpModelName=");
        sbD.append(this.mNlpModelName);
        sbD.append(",mLensId=");
        sbD.append(this.mLensId);
        sbD.append(",mMaxAlternatives=");
        sbD.append(this.mMaxAlternatives);
        sbD.append(",mShouldReturnWordTimeOffsets=");
        sbD.append(this.mShouldReturnWordTimeOffsets);
        sbD.append(",mShouldUseAutomaticPunctuations=");
        sbD.append(this.mShouldUseAutomaticPunctuations);
        sbD.append(",mShouldUseSeperateRecognitionPerChannel=");
        sbD.append(this.mShouldUseSeperateRecognitionPerChannel);
        sbD.append(",mAudioChannelCount=");
        sbD.append(this.mAudioChannelCount);
        sbD.append(",mKeywordsModels=");
        sbD.append(this.mKeywordsModels);
        sbD.append(",mIntentsModels=");
        sbD.append(this.mIntentsModels);
        sbD.append(",mAsrModelName=");
        sbD.append(this.mAsrModelName);
        sbD.append(",mCustomRoute=");
        sbD.append(this.mCustomRoute);
        sbD.append(",mSpeechContexts=");
        return E.o(sbD, this.mSpeechContexts, "}");
    }
}
