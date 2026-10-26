package com.quicinc.voice.activation.soundmodel;

import android.os.Bundle;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

/* JADX INFO: loaded from: classes.dex */
public final class SoundModelCodec {
    public static native int createFirstStageSM(BigSoundModel bigSoundModel, ByteBuffer byteBuffer);

    public static native int createUserModel(ByteBuffer byteBuffer, String str, String str2, int i2, int i3, ShortBuffer[] shortBufferArr, ByteBuffer byteBuffer2, ConfidenceData confidenceData);

    public static native int createUserModel(ByteBuffer byteBuffer, String str, String str2, int i2, ShortBuffer[] shortBufferArr, ByteBuffer byteBuffer2, ConfidenceData confidenceData);

    public static native int extend(ByteBuffer byteBuffer, String str, String str2, int i2, ShortBuffer[] shortBufferArr, ByteBuffer byteBuffer2, ConfidenceData confidenceData);

    public static native int extend(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, String str, String str2, int i2, ShortBuffer[] shortBufferArr, ByteBuffer byteBuffer3, ConfidenceData confidenceData);

    public static native int getByteBufferSize(BigSoundModel bigSoundModel);

    public static native int getMergedModelSize(ByteBuffer byteBuffer, ByteBuffer byteBuffer2);

    public static native int getSizeWhenCreateUserModel(ByteBuffer byteBuffer, String str, String str2);

    public static native int getSizeWhenCreateUserModel(ByteBuffer byteBuffer, String str, String str2, int i2);

    public static native int getSizeWhenExtended(ByteBuffer byteBuffer, String str, String str2);

    public static native int getSizeWhenExtended(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, String str, String str2);

    public static native int mergeModel(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3);

    public static native int query(ByteBuffer byteBuffer, BigSoundModel bigSoundModel);

    public static native int setSMLPDKParams(Bundle bundle);

    public static native int setSMLTIParams(Bundle bundle);

    public static native int verifyUserRecording(ByteBuffer byteBuffer, String str, ShortBuffer shortBuffer, int i2, ConfidenceData confidenceData);

    public static native int verifyUserRecordingEnergyBased(Bundle bundle, ShortBuffer shortBuffer, ConfidenceData confidenceData);
}
