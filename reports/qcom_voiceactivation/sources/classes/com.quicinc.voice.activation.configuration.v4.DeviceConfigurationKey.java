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
public final class DeviceConfigurationKey implements g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final DeviceConfigurationKey f398a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final DeviceConfigurationKey f399b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final DeviceConfigurationKey f400c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final DeviceConfigurationKey f401d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final DeviceConfigurationKey f402e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final DeviceConfigurationKey f403f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final DeviceConfigurationKey f404g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final DeviceConfigurationKey f405h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final DeviceConfigurationKey f406i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final DeviceConfigurationKey f407j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final DeviceConfigurationKey f408k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final DeviceConfigurationKey f409l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ DeviceConfigurationKey[] f410m;
    private final Type mType;
    private final String mValue;

    static {
        Type type = Type.STRING;
        f398a = new DeviceConfigurationKey("UNKNOWN", 0, "UNKNOWN", type);
        Type type2 = Type.INTEGER;
        f399b = new DeviceConfigurationKey("ENROLLMENT_UTTERANCES", 1, "Enrollment.utterances", type2);
        f400c = new DeviceConfigurationKey("ENROLLMENT_NOISY_UTTERANCES", 2, "Enrollment.noisy.utterances", type2);
        f401d = new DeviceConfigurationKey("ENROLLMENT_MAX_UTTERANCES", 3, "Enrollment.max.utterances", type2);
        f402e = new DeviceConfigurationKey("ENROLLMENT_AUDIO_SOURCE", 4, "Enrollment.audiosource", type);
        f403f = new DeviceConfigurationKey("TRAINING_TIMEOUT", 5, "Enrollment.timeout", type2);
        f404g = new DeviceConfigurationKey("HISTORY_BUFFER_DURATION", 6, "HistoryBufferDuration", type2);
        f405h = new DeviceConfigurationKey("PRE_ROLL_DURATION", 7, "PreRollDuration", type2);
        Type type3 = Type.BOOLEAN;
        f406i = new DeviceConfigurationKey("UV_ANTISPOOF_ENABLE", 8, "UV.antiSpoofEnable", type3);
        f407j = new DeviceConfigurationKey("UV_ANTISPOOF_THRESHOLD", 9, "UV.antiSpoofThreshold", Type.FLOAT);
        f408k = new DeviceConfigurationKey("TRAINING_POST_ROLL_PADDING", 10, "Enrollment.trainingPostRollPadding", type2);
        f409l = new DeviceConfigurationKey("S_STAGE_BYPASS", 11, "SecondStage.bypass", type3);
        f410m = c();
    }

    public DeviceConfigurationKey(String str, int i2, String str2, Type type) {
        super(str, i2);
        this.mValue = str2;
        this.mType = type;
    }

    public static /* synthetic */ DeviceConfigurationKey[] c() {
        return new DeviceConfigurationKey[]{f398a, f399b, f400c, f401d, f402e, f403f, f404g, f405h, f406i, f407j, f408k, f409l};
    }

    public static DeviceConfigurationKey valueOf(String str) {
        return (DeviceConfigurationKey) Enum.valueOf(DeviceConfigurationKey.class, str);
    }

    public static DeviceConfigurationKey[] values() {
        return (DeviceConfigurationKey[]) f410m.clone();
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
