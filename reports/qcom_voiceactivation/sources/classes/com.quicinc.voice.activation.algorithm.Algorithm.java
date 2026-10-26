package com.quicinc.voice.activation.algorithm;

import G.q;
import android.os.Bundle;
import com.quicinc.voice.activation.configuration.v4.DeviceConfigurationKey;
import com.quicinc.voice.activation.configuration.v4.ModelConfigurationKey;
import com.quicinc.voice.activation.soundmodel.AlgorithmType;
import com.quicinc.voice.activation.soundmodel.ConfigurationId;
import com.quicinc.voice.activation.soundmodel.SoundModel;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;
import p009j.f;
import p009j.i;

/* JADX INFO: loaded from: classes.dex */
public class Algorithm {
    private static final String KEY_ANTISPOOF_ENABLE = "antiSpoofEnable";
    private static final String KEY_ANTISPOOF_THRESHOLD = "antiSpoofThreshold";
    private static final String KEY_DNNUV_ALPHA = "dnnuv.alpha";
    private static final String KEY_DNNUV_BETA = "dnnuv.beta";
    private static final String KEY_DNNUV_GAMMA = "dnnuv.gamma";
    private static final String KEY_KW_THRESHOLD = "kwThreshold";
    private static final String KEY_MS_THRESHOLDS = "1P5.thresholds";
    private static final String KEY_PADDING_TYPE = "padding.type";
    private static final String KEY_PLDA_ENABLE = "pldaEnable";
    private static final String KEY_TIVOP_ALPHA = "tivop.alpha";
    private static final String KEY_UV72_ALPHA = "uv72.alpha";
    private static final String KEY_UV72_BETA = "uv72.beta";
    private static final String KEY_UV_THRESHOLD = "uvThreshold";
    private static final String KEY_VOP_ALPHA = "vop.alpha";
    private static final String KEY_VOP_BETA = "vop.beta";
    private static final String KEY_VOP_DELTA = "vop.delta";
    private static final String KEY_VOP_GAMMA = "vop.gamma";
    private final Bundle mConfiguration;
    private final boolean mIsGeneralUV;
    private boolean mIsInited;
    private long mNativeContext;
    private final boolean mNeedFirstStageUVScore;
    private Bundle mParams;
    private SoundModel mSoundModel;

    public Algorithm(SoundModel soundModel, Bundle bundle, boolean z2) {
        this(soundModel, bundle, z2, false);
    }

    private ByteBuffer convertByteBuffer(f fVar) {
        return ByteBuffer.wrap(fVar.a(), fVar.d(), fVar.c());
    }

    private int getThreshold() {
        ConfigurationId configurationIdD = getAlgorithmType().d();
        int i2 = (configurationIdD == ConfigurationId.SML_ID_SVA_S_STAGE_PDK || configurationIdD == ConfigurationId.SML_ID_SVA_S_STAGE_RNN) ? this.mConfiguration.getInt(ModelConfigurationKey.f445e.b()) : 0;
        q.h(ModelConfigurationKey.f445e.b() + "=" + i2);
        return i2;
    }

    private boolean isMiddleStagePDK() {
        return getAlgorithmType().d() == ConfigurationId.SML_ID_SVA_1P5_STAGE_PDK;
    }

    private boolean isSecondStageUser() {
        return getAlgorithmType().d() == ConfigurationId.SML_ID_SVA_S_STAGE_USER;
    }

    public boolean a() {
        return this.mNeedFirstStageUVScore;
    }

    public native int cancel();

    public AlgorithmResponseStatus cancelAlgorithm() {
        return AlgorithmResponseStatus.b(cancel());
    }

    public native int deinit();

    public AlgorithmResponseStatus deinitAlgorithm() {
        AlgorithmResponseStatus algorithmResponseStatusB = AlgorithmResponseStatus.b(deinit());
        if (algorithmResponseStatusB.c()) {
            this.mIsInited = false;
        }
        return algorithmResponseStatusB;
    }

    public AlgorithmType getAlgorithmType() {
        return this.mSoundModel.getAlgorithmType();
    }

    public int getPostTolerance() {
        int i2;
        ConfigurationId configurationIdD = getAlgorithmType().d();
        if (configurationIdD == ConfigurationId.SML_ID_SVA_S_STAGE_PDK || configurationIdD == ConfigurationId.SML_ID_SVA_S_STAGE_RNN) {
            i2 = this.mConfiguration.getInt(ModelConfigurationKey.f449i.b());
        } else if (configurationIdD == ConfigurationId.SML_ID_SVA_S_STAGE_USER) {
            i2 = this.mConfiguration.getInt(ModelConfigurationKey.f451k.b());
        } else {
            i2 = configurationIdD == ConfigurationId.SML_ID_SVA_1P5_STAGE_PDK ? this.mConfiguration.getInt(ModelConfigurationKey.f449i.b()) : 0;
        }
        if (i2 < 0) {
            i2 = 440;
        }
        return i2 * i.j();
    }

    public int getPreTolerance() {
        int i2;
        ConfigurationId configurationIdD = getAlgorithmType().d();
        if (configurationIdD == ConfigurationId.SML_ID_SVA_S_STAGE_PDK || configurationIdD == ConfigurationId.SML_ID_SVA_S_STAGE_RNN) {
            i2 = this.mConfiguration.getInt(ModelConfigurationKey.f448h.b());
        } else if (configurationIdD == ConfigurationId.SML_ID_SVA_S_STAGE_USER) {
            i2 = this.mConfiguration.getInt(ModelConfigurationKey.f450j.b());
        } else {
            i2 = configurationIdD == ConfigurationId.SML_ID_SVA_1P5_STAGE_PDK ? this.mConfiguration.getInt(ModelConfigurationKey.f448h.b()) : 0;
        }
        if (i2 < 0) {
            i2 = 300;
        }
        return i2 * i.j();
    }

    public native int init(ByteBuffer byteBuffer, int i2, int i3);

    public AlgorithmResponseStatus initAlgorithm() {
        if (this.mIsInited) {
            return AlgorithmResponseStatus.ALGORITHM_SUCCESS;
        }
        int iOrdinal = this.mSoundModel.getAlgorithmType().ordinal();
        int size = this.mSoundModel.getSize();
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(size);
        byteBufferAllocateDirect.put(this.mSoundModel.getData());
        AlgorithmResponseStatus algorithmResponseStatusB = AlgorithmResponseStatus.b(init(byteBufferAllocateDirect, size, iOrdinal));
        if (algorithmResponseStatusB.c()) {
            this.mIsInited = true;
        }
        return algorithmResponseStatusB;
    }

    public boolean isGeneralUV() {
        return this.mIsGeneralUV;
    }

    public native int process(ShortBuffer shortBuffer, Bundle bundle, Bundle bundle2, int i2);

    public AlgorithmResponseStatus processAlgorithm(f fVar, Bundle bundle, Bundle bundle2) {
        ShortBuffer shortBufferAsShortBuffer = convertByteBuffer(fVar).order(ByteOrder.nativeOrder()).asShortBuffer();
        short[] sArr = new short[shortBufferAsShortBuffer.capacity()];
        shortBufferAsShortBuffer.get(sArr);
        ShortBuffer shortBufferWrap = ShortBuffer.wrap(sArr);
        q.h("shortBuffer=" + shortBufferWrap.toString() + ", hasArrays=" + shortBufferWrap.hasArray());
        StringBuilder sb = new StringBuilder();
        sb.append("ProcessJNI(");
        sb.append(getAlgorithmType());
        sb.append(")");
        q.d(sb.toString());
        int iProcess = process(shortBufferWrap, bundle, bundle2, fVar.b().ordinal());
        q.m();
        return AlgorithmResponseStatus.b(iProcess);
    }

    public void replaceSoundModel(SoundModel soundModel) {
        q.g("replaceSoundModel");
        this.mSoundModel = soundModel;
    }

    public native int start(Bundle bundle);

    public AlgorithmResponseStatus startAlgorithm() {
        if (this.mParams == null) {
            this.mParams = new Bundle();
            if (isSecondStageUser()) {
                this.mParams.putFloat(KEY_UV_THRESHOLD, this.mConfiguration.getFloat(ModelConfigurationKey.f446f.b()));
                this.mParams.putFloat(KEY_VOP_ALPHA, this.mConfiguration.getFloat(ModelConfigurationKey.f452l.b()));
                this.mParams.putFloat(KEY_VOP_BETA, this.mConfiguration.getFloat(ModelConfigurationKey.f453m.b()));
                this.mParams.putFloat(KEY_VOP_GAMMA, this.mConfiguration.getFloat(ModelConfigurationKey.f454n.b()));
                this.mParams.putFloat(KEY_VOP_DELTA, this.mConfiguration.getFloat(ModelConfigurationKey.f455o.b()));
                this.mParams.putFloat(KEY_DNNUV_ALPHA, this.mConfiguration.getFloat(ModelConfigurationKey.f456p.b()));
                this.mParams.putFloat(KEY_DNNUV_BETA, this.mConfiguration.getFloat(ModelConfigurationKey.f457q.b()));
                this.mParams.putFloat(KEY_DNNUV_GAMMA, this.mConfiguration.getFloat(ModelConfigurationKey.f458r.b()));
                this.mParams.putFloat(KEY_TIVOP_ALPHA, this.mConfiguration.getFloat(ModelConfigurationKey.f459s.b()));
                this.mParams.putBoolean(KEY_PLDA_ENABLE, this.mConfiguration.getBoolean(ModelConfigurationKey.f460t.b()));
                this.mParams.putFloat(KEY_ANTISPOOF_THRESHOLD, this.mConfiguration.getFloat(DeviceConfigurationKey.f407j.b()));
                this.mParams.putBoolean(KEY_ANTISPOOF_ENABLE, this.mConfiguration.getBoolean(DeviceConfigurationKey.f406i.b()));
                this.mParams.putFloat(KEY_UV72_ALPHA, this.mConfiguration.getFloat(ModelConfigurationKey.f433G.b()));
                this.mParams.putFloat(KEY_UV72_BETA, this.mConfiguration.getFloat(ModelConfigurationKey.f434H.b()));
                this.mParams.putInt(KEY_PADDING_TYPE, this.mConfiguration.getInt(ModelConfigurationKey.f461u.b()));
            } else if (isMiddleStagePDK()) {
                this.mParams.putIntArray(KEY_MS_THRESHOLDS, this.mConfiguration.getIntArray(ModelConfigurationKey.f429C.b()));
            } else {
                this.mParams.putInt(KEY_KW_THRESHOLD, getThreshold());
            }
        }
        q.h("startAlgorithm " + getAlgorithmType().e() + " config= " + this.mParams);
        return AlgorithmResponseStatus.b(start(this.mParams));
    }

    public native int stop();

    public AlgorithmResponseStatus stopAlgorithm() {
        return AlgorithmResponseStatus.b(stop());
    }

    public AlgorithmResponseStatus updateTIUVThreshold(boolean z2) {
        q.h("mConfiguration =" + this.mConfiguration);
        if (z2) {
            this.mParams.putFloat(KEY_UV_THRESHOLD, this.mConfiguration.getFloat(ModelConfigurationKey.f432F.b()));
        } else {
            this.mParams.putFloat(KEY_UV_THRESHOLD, this.mConfiguration.getFloat(ModelConfigurationKey.f446f.b()));
        }
        q.h("updateTIUVThreshold " + getAlgorithmType().e() + " config= " + this.mParams + ",second round=" + z2);
        return AlgorithmResponseStatus.b(start(this.mParams));
    }

    public Algorithm(SoundModel soundModel, Bundle bundle, boolean z2, boolean z3) {
        this.mIsInited = false;
        this.mSoundModel = soundModel;
        this.mConfiguration = bundle;
        this.mNeedFirstStageUVScore = z2;
        this.mIsGeneralUV = z3;
    }
}
