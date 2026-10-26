package com.quicinc.voice.activation.configuration.v4;

import com.quicinc.voice.activation.xmlhelper.Type;
import p011l.g;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'a' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes.dex */
public final class SmlConfigurationKey implements g {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final SmlConfigurationKey f467A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public static final SmlConfigurationKey f468B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final SmlConfigurationKey f469C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final SmlConfigurationKey f470D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final SmlConfigurationKey f471E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final SmlConfigurationKey f472F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public static final SmlConfigurationKey f473G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public static final SmlConfigurationKey f474H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public static final SmlConfigurationKey f475I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final SmlConfigurationKey f476J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public static final SmlConfigurationKey f477K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public static final SmlConfigurationKey f478L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public static final SmlConfigurationKey f479M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public static final SmlConfigurationKey f480N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public static final SmlConfigurationKey f481O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public static final /* synthetic */ SmlConfigurationKey[] f482P;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SmlConfigurationKey f483a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final SmlConfigurationKey f484b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final SmlConfigurationKey f485c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final SmlConfigurationKey f486d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final SmlConfigurationKey f487e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final SmlConfigurationKey f488f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final SmlConfigurationKey f489g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final SmlConfigurationKey f490h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final SmlConfigurationKey f491i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final SmlConfigurationKey f492j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final SmlConfigurationKey f493k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final SmlConfigurationKey f494l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final SmlConfigurationKey f495m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final SmlConfigurationKey f496n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final SmlConfigurationKey f497o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final SmlConfigurationKey f498p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final SmlConfigurationKey f499q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final SmlConfigurationKey f500r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final SmlConfigurationKey f501s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final SmlConfigurationKey f502t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final SmlConfigurationKey f503u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final SmlConfigurationKey f504v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final SmlConfigurationKey f505w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final SmlConfigurationKey f506x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final SmlConfigurationKey f507y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final SmlConfigurationKey f508z;
    private Type mType;
    private String mValue;

    static {
        Type type = Type.INTEGER;
        f483a = new SmlConfigurationKey("TRAINING_NUMGUARDFRAMES_FOR_FIRSTSTAGE_EPD", 0, "Training.numGuardFrames_for_firstStage_epd", type);
        Type type2 = Type.BOOLEAN;
        f484b = new SmlConfigurationKey("TRAINING_CHECK_CHOPPED", 1, "Training.check_chopped", type2);
        f485c = new SmlConfigurationKey("TRAINING_CHOPPING_FRAME_THRESHOLD", 2, "Training.chopping_frame_threshold", type);
        f486d = new SmlConfigurationKey("TRAINING_FIRSTSTAGE_EPD_ENABLE", 3, "Training.firstStageEPDEnable", type2);
        Type type3 = Type.FLOAT;
        f487e = new SmlConfigurationKey("TRAINING_SNR_THRESHOLD_FOR_NOISY_SAMPLES", 4, "Training.snr_threshold_for_noisy_samples", type3);
        f488f = new SmlConfigurationKey("TRAINING_PCM_NORMALIZATION", 5, "Training.pcmNormalization", type2);
        f489g = new SmlConfigurationKey("TRAINING_CHECK_CLIPPED", 6, "Training.checkClipped", type2);
        f490h = new SmlConfigurationKey("TRAINING_N_CLIPPING_THRESHOLD", 7, "Training.nClippingThreshold", type);
        f491i = new SmlConfigurationKey("TRAINING_CLIPPING_RATIO_THRESHOLD", 8, "Training.clippingRatioThreshold", type3);
        f492j = new SmlConfigurationKey("TRAINING_ENABLE_ZERO_PROCESSING_FOR_PDKXS", 9, "Training.enableZeroProcessingForPdkXS", type2);
        f493k = new SmlConfigurationKey("TRAINING_N_ZEROS_PADDING_FRAMES", 10, "Training.nZerosPaddingFrames", type);
        f494l = new SmlConfigurationKey("TRAINING_B_ENABLE_EPD_FOR_UV_72", 11, "Training.bEnableEPDForUv72", type2);
        f495m = new SmlConfigurationKey("EPD_MIN_SNR_ONSET", 12, "EPD.minSnrOnset", type3);
        f496n = new SmlConfigurationKey("EPD_MIN_SNR_LEAVE", 13, "EPD.minSnrLeave", type3);
        f497o = new SmlConfigurationKey("EPD_SNR_FLOOR", 14, "EPD.snrFloor", type3);
        f498p = new SmlConfigurationKey("EPD_SNR_THRESHOLDS", 15, "EPD.snrThresholds", type3);
        f499q = new SmlConfigurationKey("EPD_FORGETTING_FACTOR_NOISE", 16, "EPD.forgettingFactorNoise", type3);
        f500r = new SmlConfigurationKey("EPD_NUM_FRAME_TRANSIENT_FRAME", 17, "EPD.numFrameTransientFrame", type);
        f501s = new SmlConfigurationKey("EPD_MIN_ENERGY_FRAME_RATIO", 18, "EPD.minEnergyFrameRatio", type3);
        f502t = new SmlConfigurationKey("EPD_MIN_NOISE_ENERGY", 19, "EPD.minNoiseEnergy", type3);
        f503u = new SmlConfigurationKey("EPD_NUM_MIN_FRAMES_INPHRASE", 20, "EPD.numMinFramesInPhrase", type);
        f504v = new SmlConfigurationKey("EPD_NUM_MIN_FRAMES_INSPEECH", 21, "EPD.numMinFramesInSpeech", type);
        f505w = new SmlConfigurationKey("EPD_NUM_MAX_FRAMES_INSPEECH_GAP", 22, "EPD.numMaxFrameInSpeechGap", type);
        f506x = new SmlConfigurationKey("EPD_NUM_FRAMES_INHEAD", 23, "EPD.numFramesInHead", type);
        f507y = new SmlConfigurationKey("EPD_NUM_FRAMES_INTAIL", 24, "EPD.numFramesInTail", type);
        f508z = new SmlConfigurationKey("EPD_PRE_EMPHASIZING", 25, "EPD.preEmphasize", type);
        f467A = new SmlConfigurationKey("EPD_NUM_MAX_FRAMES", 26, "EPD.numMaxFrames", type);
        f468B = new SmlConfigurationKey("ADA_ENABLE", 27, "Ada.enable", type2);
        Type type4 = Type.STRING;
        f469C = new SmlConfigurationKey("ADA_CAR_NOISE", 28, "Ada.car_noise", type4);
        f470D = new SmlConfigurationKey("ADA_MUSIC_NOISE", 29, "Ada.music_noise", type4);
        f471E = new SmlConfigurationKey("ADA_RADIO_NOISE", 30, "Ada.radio_noise", type4);
        f472F = new SmlConfigurationKey("ADA_PARTY_NOISE", 31, "Ada.party_noise", type4);
        f473G = new SmlConfigurationKey("ADA_NUM_CLEAN_REPETITION", 32, "Ada.numCleanRepetition", type);
        f474H = new SmlConfigurationKey("ADA_ENABLE_PITCH", 33, "Ada.enablePitch", type2);
        f475I = new SmlConfigurationKey("ADA_ENABLE_TEMPO", 34, "Ada.enableTempo", type2);
        f476J = new SmlConfigurationKey("ADA_ENABLE_LEVEL", 35, "Ada.enableLevel", type2);
        f477K = new SmlConfigurationKey("ADA_ENABLE_REVERB", 36, "Ada.enableReverb", type2);
        f478L = new SmlConfigurationKey("ADA_NOISE_TYPE_VOP", 37, "Ada.noise_type_vop", type4);
        f479M = new SmlConfigurationKey("ADA_NOISE_SNR_VOP", 38, "Ada.noise_snr_vop", type);
        f480N = new SmlConfigurationKey("TRAINING_NUMGUARDFRAMESFOREPD", 39, "Training.numGuardFramesForEPD", type);
        f481O = new SmlConfigurationKey("TRAINING_PADDING_TYPE", 40, "Training.padding_type", type);
        f482P = c();
    }

    public SmlConfigurationKey(String str, int i2, String str2, Type type) {
        super(str, i2);
        this.mValue = str2;
        this.mType = type;
    }

    public static /* synthetic */ SmlConfigurationKey[] c() {
        return new SmlConfigurationKey[]{f483a, f484b, f485c, f486d, f487e, f488f, f489g, f490h, f491i, f492j, f493k, f494l, f495m, f496n, f497o, f498p, f499q, f500r, f501s, f502t, f503u, f504v, f505w, f506x, f507y, f508z, f467A, f468B, f469C, f470D, f471E, f472F, f473G, f474H, f475I, f476J, f477K, f478L, f479M, f480N, f481O};
    }

    public static SmlConfigurationKey valueOf(String str) {
        return (SmlConfigurationKey) Enum.valueOf(SmlConfigurationKey.class, str);
    }

    public static SmlConfigurationKey[] values() {
        return (SmlConfigurationKey[]) f482P.clone();
    }

    @Override // p011l.g
    public Type a() {
        return this.mType;
    }

    @Override // p011l.g
    public String b() {
        return this.mValue;
    }
}
