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
public final class EPDConfigurationKey implements g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final EPDConfigurationKey f411a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final EPDConfigurationKey f412b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final EPDConfigurationKey f413c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final EPDConfigurationKey f414d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final EPDConfigurationKey f415e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final EPDConfigurationKey f416f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final EPDConfigurationKey f417g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final EPDConfigurationKey f418h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final EPDConfigurationKey f419i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final EPDConfigurationKey f420j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final EPDConfigurationKey f421k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final EPDConfigurationKey f422l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final EPDConfigurationKey f423m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final EPDConfigurationKey f424n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final EPDConfigurationKey f425o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ EPDConfigurationKey[] f426p;
    private Type mType;
    private String mValue;

    static {
        Type type = Type.FLOAT;
        f411a = new EPDConfigurationKey("EPD_MIN_SNR_ONSET", 0, "EPD.minSnrOnset", type);
        f412b = new EPDConfigurationKey("EPD_MIN_SNR_LEAVE", 1, "EPD.minSnrLeave", type);
        f413c = new EPDConfigurationKey("EPD_SNR_FLOOR", 2, "EPD.snrFloor", type);
        f414d = new EPDConfigurationKey("EPD_SNR_THRESHOLDS", 3, "EPD.snrThresholds", type);
        f415e = new EPDConfigurationKey("EPD_FORGETTING_FACTOR_NOISE", 4, "EPD.forgettingFactorNoise", type);
        Type type2 = Type.INTEGER;
        f416f = new EPDConfigurationKey("EPD_NUM_FRAME_TRANSIENT_FRAME", 5, "EPD.numFrameTransientFrame", type2);
        f417g = new EPDConfigurationKey("EPD_MIN_ENERGY_FRAME_RATIO", 6, "EPD.minEnergyFrameRatio", type);
        f418h = new EPDConfigurationKey("EPD_MIN_NOISE_ENERGY", 7, "EPD.minNoiseEnergy", type);
        f419i = new EPDConfigurationKey("EPD_NUM_MIN_FRAMES_INPHRASE", 8, "EPD.numMinFramesInPhrase", type2);
        f420j = new EPDConfigurationKey("EPD_NUM_MIN_FRAMES_INSPEECH", 9, "EPD.numMinFramesInSpeech", type2);
        f421k = new EPDConfigurationKey("EPD_NUM_MAX_FRAMES_INSPEECH_GAP", 10, "EPD.numMaxFrameInSpeechGap", type2);
        f422l = new EPDConfigurationKey("EPD_NUM_FRAMES_INHEAD", 11, "EPD.numFramesInHead", type2);
        f423m = new EPDConfigurationKey("EPD_NUM_FRAMES_INTAIL", 12, "EPD.numFramesInTail", type2);
        f424n = new EPDConfigurationKey("EPD_PRE_EMPHASIZING", 13, "EPD.preEmphasize", type2);
        f425o = new EPDConfigurationKey("EPD_NUM_MAX_FRAMES", 14, "EPD.numMaxFrames", type2);
        f426p = c();
    }

    public EPDConfigurationKey(String str, int i2, String str2, Type type) {
        super(str, i2);
        this.mValue = str2;
        this.mType = type;
    }

    public static /* synthetic */ EPDConfigurationKey[] c() {
        return new EPDConfigurationKey[]{f411a, f412b, f413c, f414d, f415e, f416f, f417g, f418h, f419i, f420j, f421k, f422l, f423m, f424n, f425o};
    }

    public static EPDConfigurationKey valueOf(String str) {
        return (EPDConfigurationKey) Enum.valueOf(EPDConfigurationKey.class, str);
    }

    public static EPDConfigurationKey[] values() {
        return (EPDConfigurationKey[]) f426p.clone();
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
