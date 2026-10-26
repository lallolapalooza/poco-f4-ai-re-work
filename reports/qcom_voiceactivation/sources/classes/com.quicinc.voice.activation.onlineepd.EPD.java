package com.quicinc.voice.activation.onlineepd;

import G.q;
import android.os.Bundle;
import com.quicinc.voice.activation.configuration.v4.EPDConfigurationKey;
import com.quicinc.voice.activation.configuration.v4.ModelConfigurationKey;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import s.a;

/* JADX INFO: loaded from: classes.dex */
public class EPD {
    public static final a EMPTY = createProcessResult(Integer.MIN_VALUE, null);
    private static final String nativeForgettingFactorNoise = "EPD.forgettingFactorNoise";
    private static final String nativeKeywordThreshold = "EPD.keywordThreshold";
    private static final String nativeMinEnergyFrameRatio = "EPD.minEnergyFrameRatio";
    private static final String nativeMinNoiseEnergy = "EPD.minNoiseEnergy";
    private static final String nativeMinSnrLeave = "EPD.minSnrLeave";
    private static final String nativeMinSnrOnset = "EPD.minSnrOnset";
    private static final String nativeNumFrameTransientFrame = "EPD.numFrameTransientFrame";
    private static final String nativeNumFramesInHead = "EPD.numFramesInHead";
    private static final String nativeNumFramesInTail = "EPD.numFramesInTail";
    private static final String nativeNumMaxFrameInSpeechGap = "EPD.numMaxFrameInSpeechGap";
    private static final String nativeNumMaxFrames = "EPD.numMaxFrames";
    private static final String nativeNumMinFramesInPhrase = "EPD.numMinFramesInPhrase";
    private static final String nativeNumMinFramesInSpeech = "EPD.numMinFramesInSpeech";
    private static final String nativePreEmphasize = "EPD.preEmphasize";
    private static final String nativeSnrFloor = "EPD.snrFloor";
    private static final String nativeSnrThresholds = "EPD.snrThresholds";
    private long mNativeContext;
    private ByteBuffer mSoundModel;
    private final Bundle mBundle = new Bundle();
    private final Bundle mParams = new Bundle();

    public EPD(ByteBuffer byteBuffer, Bundle bundle) {
        this.mSoundModel = byteBuffer;
        convertEPDConfiguration(bundle);
    }

    private void convertEPDConfiguration(Bundle bundle) {
        this.mParams.putFloat(nativeMinSnrOnset, bundle.getFloat(EPDConfigurationKey.f411a.b()));
        this.mParams.putFloat(nativeMinSnrLeave, bundle.getFloat(EPDConfigurationKey.f412b.b()));
        this.mParams.putFloat(nativeSnrFloor, bundle.getFloat(EPDConfigurationKey.f413c.b()));
        this.mParams.putFloat(nativeSnrThresholds, bundle.getFloat(EPDConfigurationKey.f414d.b()));
        this.mParams.putFloat(nativeForgettingFactorNoise, bundle.getFloat(EPDConfigurationKey.f415e.b()));
        this.mParams.putFloat(nativeMinEnergyFrameRatio, bundle.getFloat(EPDConfigurationKey.f417g.b()));
        this.mParams.putFloat(nativeMinNoiseEnergy, bundle.getFloat(EPDConfigurationKey.f418h.b()));
        this.mParams.putInt(nativeNumFrameTransientFrame, bundle.getInt(EPDConfigurationKey.f416f.b()));
        this.mParams.putInt(nativeNumMinFramesInPhrase, bundle.getInt(EPDConfigurationKey.f419i.b()));
        this.mParams.putInt(nativeNumMinFramesInSpeech, bundle.getInt(EPDConfigurationKey.f420j.b()));
        this.mParams.putInt(nativeNumMaxFrameInSpeechGap, bundle.getInt(EPDConfigurationKey.f421k.b()));
        this.mParams.putInt(nativeNumFramesInHead, bundle.getInt(EPDConfigurationKey.f422l.b()));
        this.mParams.putInt(nativeNumFramesInTail, bundle.getInt(EPDConfigurationKey.f423m.b()));
        this.mParams.putInt(nativePreEmphasize, bundle.getInt(EPDConfigurationKey.f424n.b()));
        this.mParams.putInt(nativeNumMaxFrames, bundle.getInt(EPDConfigurationKey.f425o.b()));
        this.mParams.putInt(nativeKeywordThreshold, bundle.getInt(ModelConfigurationKey.f447g.b()));
        q.v("EPD convertEPDConfiguration params = " + this.mParams);
    }

    private static a createProcessResult(int i2, Bundle bundle) {
        return new a(i2, bundle);
    }

    public native int init(Bundle bundle, ByteBuffer byteBuffer);

    public a initEPD() {
        int iInit;
        synchronized (this) {
            iInit = init(this.mParams, this.mSoundModel);
        }
        q.v("EPD init result = " + iInit);
        return createProcessResult(iInit, null);
    }

    public native int process(ShortBuffer shortBuffer, Bundle bundle);

    public a processEPD(ShortBuffer shortBuffer) {
        int iProcess;
        synchronized (this) {
            iProcess = process(shortBuffer, this.mBundle);
        }
        q.v("EPD process result = " + iProcess + " bundle = " + this.mBundle);
        return createProcessResult(iProcess, this.mBundle);
    }

    public a reInitEPD() {
        int iReinit;
        synchronized (this) {
            iReinit = reinit();
        }
        q.v("EPD reinit result = " + iReinit);
        return createProcessResult(iReinit, null);
    }

    public native int reinit();

    public native int release();

    public a releaseEPD() {
        int iRelease;
        synchronized (this) {
            iRelease = release();
        }
        q.v("EPD release result = " + iRelease);
        return createProcessResult(iRelease, null);
    }
}
