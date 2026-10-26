package com.quicinc.voice.activation.soundmodel;

import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'b' uses external variables
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
public final class AlgorithmType {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final AlgorithmType f599a = new AlgorithmType("UNKNOWN", 0, ConfigurationId.UNKNOWN, 0, 0, "UNKNOWN");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AlgorithmType f600b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final AlgorithmType f601c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final AlgorithmType f602d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final AlgorithmType f603e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final AlgorithmType f604f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final AlgorithmType f605g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final AlgorithmType f606h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final AlgorithmType f607i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final AlgorithmType f608j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final AlgorithmType f609k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final AlgorithmType f610l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final AlgorithmType f611m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final AlgorithmType f612n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final AlgorithmType f613o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final AlgorithmType f614p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final AlgorithmType f615q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final AlgorithmType f616r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final AlgorithmType f617s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final AlgorithmType f618t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final AlgorithmType f619u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final AlgorithmType f620v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final AlgorithmType f621w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final AlgorithmType f622x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final /* synthetic */ AlgorithmType[] f623y;
    private final ConfigurationId mConfigurationId;
    private final String mDescription;
    private final int mMajor;
    private final int mMinor;

    static {
        ConfigurationId configurationId = ConfigurationId.SML_ID_SVA_F_STAGE;
        f600b = new AlgorithmType("GMM", 1, configurationId, 3, 0, "GMM");
        f601c = new AlgorithmType("PDK5_XS", 2, configurationId, 5, 0, "PDK5_XS");
        f602d = new AlgorithmType("PDK6_XS", 3, configurationId, 6, 0, "PDK6_XS");
        f603e = new AlgorithmType("MFCN", 4, configurationId, 8, 0, "MFCN");
        ConfigurationId configurationId2 = ConfigurationId.SML_ID_SVA_1P5_STAGE_PDK;
        f604f = new AlgorithmType("PDK5XS_1P5", 5, configurationId2, 5, 0, "PDK5XS_1P5");
        f605g = new AlgorithmType("PDK6XS_1P5", 6, configurationId2, 6, 0, "PDK6XS_1P5");
        ConfigurationId configurationId3 = ConfigurationId.SML_ID_SVA_S_STAGE_USER;
        f606h = new AlgorithmType("VOP", 7, configurationId3, 3, 0, "VOP");
        ConfigurationId configurationId4 = ConfigurationId.SML_ID_SVA_S_STAGE_UBM;
        f607i = new AlgorithmType("UBM_GMM", 8, configurationId4, 3, 0, "UBM_GMM");
        f608j = new AlgorithmType("UBM_DNN", 9, configurationId4, 7, 0, "UBM_DNN");
        f609k = new AlgorithmType("UBM_TIVOP", 10, configurationId4, 7, 1, "UBM_TIVOP");
        f610l = new AlgorithmType("UBM_DNN_V2P6", 11, configurationId4, 8, 0, "UBM_DNN_V2P6");
        f611m = new AlgorithmType("UBM_TIVOP_V2P1", 12, configurationId4, 8, 1, "UBM_TIVOP_V2P1");
        f612n = new AlgorithmType("UBM_IDUV", 13, configurationId4, 7, 2, "UBM_IDUV");
        ConfigurationId configurationId5 = ConfigurationId.SML_ID_SVA_S_STAGE_PDK;
        f613o = new AlgorithmType("PDK", 14, configurationId5, 3, 0, "PDK");
        f614p = new AlgorithmType("RNN", 15, ConfigurationId.SML_ID_SVA_S_STAGE_RNN, 3, 0, "RNN");
        f615q = new AlgorithmType("PDK5_STHAL", 16, configurationId5, 5, 1, "PDK5_STHAL");
        f616r = new AlgorithmType("PDK5_QVA", 17, configurationId5, 5, 2, "PDK5_QVA");
        f617s = new AlgorithmType("PDK7", 18, configurationId5, 7, 0, "PDK7");
        f618t = new AlgorithmType("DNN_UV", 19, configurationId3, 7, 0, "DNN_UV");
        f619u = new AlgorithmType("TIVOP", 20, configurationId3, 7, 1, "TIVOP");
        f620v = new AlgorithmType("DNN_UV_V2P6", 21, configurationId3, 8, 0, "DNN_UV_V2P6");
        f621w = new AlgorithmType("TIVOP_V2P1", 22, configurationId3, 8, 1, "TIVOP_V2P1");
        f622x = new AlgorithmType("IDUV", 23, configurationId3, 7, 2, "IDUV");
        f623y = a();
    }

    public AlgorithmType(String str, int i2, ConfigurationId configurationId, int i3, int i4, String str2) {
        super(str, i2);
        this.mConfigurationId = configurationId;
        this.mMajor = i3;
        this.mMinor = i4;
        this.mDescription = str2;
    }

    public static /* synthetic */ AlgorithmType[] a() {
        return new AlgorithmType[]{f599a, f600b, f601c, f602d, f603e, f604f, f605g, f606h, f607i, f608j, f609k, f610l, f611m, f612n, f613o, f614p, f615q, f616r, f617s, f618t, f619u, f620v, f621w, f622x};
    }

    public static AlgorithmType b(ConfigurationId configurationId, int i2, int i3) {
        for (AlgorithmType algorithmType : values()) {
            if (algorithmType.mConfigurationId == configurationId && algorithmType.mMajor == i2 && algorithmType.mMinor == i3) {
                return algorithmType;
            }
        }
        return f599a;
    }

    public static AlgorithmType c(String str) {
        for (AlgorithmType algorithmType : values()) {
            if (algorithmType.mDescription.equalsIgnoreCase(str)) {
                return algorithmType;
            }
        }
        return f599a;
    }

    public static List g() {
        ArrayList arrayList = new ArrayList();
        for (AlgorithmType algorithmType : values()) {
            if (algorithmType.d() != ConfigurationId.SML_ID_SVA_F_STAGE && algorithmType.d() != ConfigurationId.SML_ID_SVA_F_STAGE_INTERNAL && algorithmType.d() != ConfigurationId.SML_ID_SVA_S_STAGE_UBM && algorithmType.d() != ConfigurationId.UNKNOWN) {
                arrayList.add(algorithmType.e());
            }
        }
        return arrayList;
    }

    public static AlgorithmType valueOf(String str) {
        return (AlgorithmType) Enum.valueOf(AlgorithmType.class, str);
    }

    public static AlgorithmType[] values() {
        return (AlgorithmType[]) f623y.clone();
    }

    public ConfigurationId d() {
        return this.mConfigurationId;
    }

    public String e() {
        return this.mDescription;
    }

    public boolean f() {
        return this.mConfigurationId == ConfigurationId.SML_ID_SVA_S_STAGE_USER;
    }
}
