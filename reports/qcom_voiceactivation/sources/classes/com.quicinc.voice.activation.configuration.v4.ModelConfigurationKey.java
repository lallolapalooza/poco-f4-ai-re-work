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
public final class ModelConfigurationKey implements g {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final ModelConfigurationKey f427A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public static final ModelConfigurationKey f428B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final ModelConfigurationKey f429C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public static final ModelConfigurationKey f430D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final ModelConfigurationKey f431E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final ModelConfigurationKey f432F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public static final ModelConfigurationKey f433G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public static final ModelConfigurationKey f434H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public static final ModelConfigurationKey f435I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final ModelConfigurationKey f436J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public static final ModelConfigurationKey f437K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public static final ModelConfigurationKey f438L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public static final ModelConfigurationKey f439M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public static final /* synthetic */ ModelConfigurationKey[] f440N;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ModelConfigurationKey f441a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ModelConfigurationKey f442b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final ModelConfigurationKey f443c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final ModelConfigurationKey f444d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final ModelConfigurationKey f445e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final ModelConfigurationKey f446f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final ModelConfigurationKey f447g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final ModelConfigurationKey f448h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final ModelConfigurationKey f449i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final ModelConfigurationKey f450j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final ModelConfigurationKey f451k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final ModelConfigurationKey f452l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final ModelConfigurationKey f453m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final ModelConfigurationKey f454n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final ModelConfigurationKey f455o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final ModelConfigurationKey f456p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final ModelConfigurationKey f457q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final ModelConfigurationKey f458r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final ModelConfigurationKey f459s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final ModelConfigurationKey f460t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final ModelConfigurationKey f461u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final ModelConfigurationKey f462v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final ModelConfigurationKey f463w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final ModelConfigurationKey f464x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final ModelConfigurationKey f465y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final ModelConfigurationKey f466z;
    private final Type mType;
    private final String mValue;

    static {
        Type type = Type.STRING;
        f441a = new ModelConfigurationKey("UNKNOWN", 0, "UNKNOWN", type);
        Type type2 = Type.INTEGER;
        f442b = new ModelConfigurationKey("F_STAGE_KW_THRESHOLD", 1, "FirstStageKW.threshold", type2);
        f443c = new ModelConfigurationKey("F_STAGE_KW_THRESHOLDS", 2, "FirstStageKW.thresholds", type);
        f444d = new ModelConfigurationKey("F_STAGE_UV_THRESHOLD", 3, "FirstStageUV.threshold", type2);
        f445e = new ModelConfigurationKey("S_STAGE_KW_THRESHOLD", 4, "SecondStageKW.threshold", type2);
        Type type3 = Type.FLOAT;
        f446f = new ModelConfigurationKey("S_STAGE_UV_THRESHOLD", 5, "SecondStageUV.threshold", type3);
        f447g = new ModelConfigurationKey("TRAINING_THRESHOLD", 6, "Enrollment.threshold", type2);
        f448h = new ModelConfigurationKey("KW_PRE_TOLERANCE", 7, "KW.preTolerance", type2);
        f449i = new ModelConfigurationKey("KW_POST_TOLERANCE", 8, "KW.postTolerance", type2);
        f450j = new ModelConfigurationKey("UV_PRE_TOLERANCE", 9, "UV.preTolerance", type2);
        f451k = new ModelConfigurationKey("UV_POST_TOLERANCE", 10, "UV.postTolerance", type2);
        f452l = new ModelConfigurationKey("UV_VOP_ALPHA", 11, "UV.VopAlpha", type3);
        f453m = new ModelConfigurationKey("UV_VOP_BETA", 12, "UV.VopBeta", type3);
        f454n = new ModelConfigurationKey("UV_VOP_GAMMA", 13, "UV.VopGamma", type3);
        f455o = new ModelConfigurationKey("UV_VOP_DELTA", 14, "UV.VopDelta", type3);
        f456p = new ModelConfigurationKey("UV_DNNUV_ALPHA", 15, "UV.DnnUvAlpha", type3);
        f457q = new ModelConfigurationKey("UV_DNNUV_BETA", 16, "UV.DnnUvBeta", type3);
        f458r = new ModelConfigurationKey("UV_DNNUV_GAMMA", 17, "UV.DnnUvGamma", type3);
        f459s = new ModelConfigurationKey("UV_TIVOP_ALPHA", 18, "UV.TivopAlpha", type3);
        Type type4 = Type.BOOLEAN;
        f460t = new ModelConfigurationKey("UV_PLDA_ENABLE", 19, "UV.pldaEnable", type4);
        f461u = new ModelConfigurationKey("UV_PADDING_TYPE", 20, "UV.paddingtype", type2);
        f462v = new ModelConfigurationKey("TRAINING_NUMGUARDFRAMES_FOR_EPD", 21, "Training.numGuardFrames_for_epd", type2);
        f463w = new ModelConfigurationKey("S_STAGE_UV_THRESHOLD_KW0", 22, "SecondStageUV.threshold.KW.0", type3);
        f464x = new ModelConfigurationKey("S_STAGE_UV_THRESHOLD_KW1", 23, "SecondStageUV.threshold.KW.1", type3);
        f465y = new ModelConfigurationKey("S_STAGE_UV_THRESHOLD_KW2", 24, "SecondStageUV.threshold.KW.2", type3);
        f466z = new ModelConfigurationKey("S_STAGE_UV_THRESHOLD_KW3", 25, "SecondStageUV.threshold.KW.3", type3);
        f427A = new ModelConfigurationKey("S_STAGE_UV_THRESHOLD_KW4", 26, "SecondStageUV.threshold.KW.4", type3);
        f428B = new ModelConfigurationKey("S_STAGE_UV_THRESHOLD_KW5", 27, "SecondStageUV.threshold.KW.5", type3);
        f429C = new ModelConfigurationKey("M_STAGE_KW_THRESHOLDS", 28, "MiddleStageKW.thresholds", type);
        f430D = new ModelConfigurationKey("TRAINING_BENABLE_AGA", 29, "Training.bEnableAGA", type4);
        f431E = new ModelConfigurationKey("TRAINING_AGATARGETENROLLMENT_RMS", 30, "Training.agaTargetEnrollmentRMS", type3);
        f432F = new ModelConfigurationKey("S_STAGE_UV_THRESHOLD_KWCMD", 31, "SecondStageUV.threshold.kwcmd", type3);
        f433G = new ModelConfigurationKey("UV_UV72_ALPHA", 32, "UV.UV72Alpha", type3);
        f434H = new ModelConfigurationKey("UV_UV72_BETA", 33, "UV.UV72Beta", type3);
        f435I = new ModelConfigurationKey("NNVAD_CMD_END_DETECTION_TIMEOUT", 34, "cmd.end.detection.timeout", type2);
        f436J = new ModelConfigurationKey("NNVAD_LEN_FUTURE", 35, "nnvad.len.future", type2);
        f437K = new ModelConfigurationKey("NNVAD_LEN_HANGOVER", 36, "nnvad.len.hangover", type2);
        f438L = new ModelConfigurationKey("NNVAD_THRESHOLD", 37, "nnvad.threshold", type2);
        f439M = new ModelConfigurationKey("NNVAD_CMD_END_DETECTION_THRESHOLD", 38, "cmd.end.detection.threshold", type2);
        f440N = c();
    }

    public ModelConfigurationKey(String str, int i2, String str2, Type type) {
        super(str, i2);
        this.mValue = str2;
        this.mType = type;
    }

    public static /* synthetic */ ModelConfigurationKey[] c() {
        return new ModelConfigurationKey[]{f441a, f442b, f443c, f444d, f445e, f446f, f447g, f448h, f449i, f450j, f451k, f452l, f453m, f454n, f455o, f456p, f457q, f458r, f459s, f460t, f461u, f462v, f463w, f464x, f465y, f466z, f427A, f428B, f429C, f430D, f431E, f432F, f433G, f434H, f435I, f436J, f437K, f438L, f439M};
    }

    public static ModelConfigurationKey d(int i2) {
        if (i2 == 0) {
            return f463w;
        }
        if (i2 == 1) {
            return f464x;
        }
        if (i2 == 2) {
            return f465y;
        }
        if (i2 == 3) {
            return f466z;
        }
        if (i2 != 4) {
            return i2 != 5 ? f441a : f428B;
        }
        return f427A;
    }

    public static ModelConfigurationKey valueOf(String str) {
        return (ModelConfigurationKey) Enum.valueOf(ModelConfigurationKey.class, str);
    }

    public static ModelConfigurationKey[] values() {
        return (ModelConfigurationKey[]) f440N.clone();
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
