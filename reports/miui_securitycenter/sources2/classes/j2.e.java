package j2;

import com.miui.antispam.policy.BlackAddressPolicy;
import com.miui.antispam.policy.BlackListPolicy;
import com.miui.antispam.policy.BlackPrefixPolicy;
import com.miui.antispam.policy.CallTransferPolicy;
import com.miui.antispam.policy.CloudBlackKeywordsPolicy;
import com.miui.antispam.policy.CloudBlackListPolicy;
import com.miui.antispam.policy.CloudWhiteKeywordsPolicy;
import com.miui.antispam.policy.CloudWhiteListPolicy;
import com.miui.antispam.policy.ContactsPolicy;
import com.miui.antispam.policy.EmptyNumberPolicy;
import com.miui.antispam.policy.KeywordsBlackListPolicy;
import com.miui.antispam.policy.KeywordsWhiteListPolicy;
import com.miui.antispam.policy.OverseaPolicy;
import com.miui.antispam.policy.ReportedNumberPolicy;
import com.miui.antispam.policy.ServiceSmsPolicy;
import com.miui.antispam.policy.SmartSmsFilterPolicy;
import com.miui.antispam.policy.StrangerPolicy;
import com.miui.antispam.policy.StrongCloudBlackListPolicy;
import com.miui.antispam.policy.StrongCloudWhiteListPolicy;
import com.miui.antispam.policy.WhiteListPolicy;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'f' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:422)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:351)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:153)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes2.dex */
public final class e {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final e f47850f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final e f47851g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final e f47852h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final e f47853i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final e f47854j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final e f47855k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final e f47856l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final e f47857m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final e f47858n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final e f47859o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final e f47860p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final e f47861q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final e f47862r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final e f47863s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final e f47864t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final e f47865u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final e f47866v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final e f47867w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final e f47868x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final e f47869y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private static final /* synthetic */ e[] f47870z;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f47871c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Class f47872d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public d f47873e;

    static {
        d dVar = d.CALL;
        e eVar = new e("EMPTY_NUMBER_POLICY", 0, 0, EmptyNumberPolicy.class, dVar);
        f47850f = eVar;
        d dVar2 = d.BOTH;
        e eVar2 = new e("STRONG_CLOUD_WHITELIST_POLICY", 1, 1, StrongCloudWhiteListPolicy.class, dVar2);
        f47851g = eVar2;
        e eVar3 = new e("STRONG_CLOUD_BLACKLIST_POLICY", 2, 2, StrongCloudBlackListPolicy.class, dVar2);
        f47852h = eVar3;
        e eVar4 = new e("WHITELIST_POLICY", 3, 3, WhiteListPolicy.class, dVar2);
        f47853i = eVar4;
        e eVar5 = new e("BLACKLIST_POLICY", 4, 4, BlackListPolicy.class, dVar2);
        f47854j = eVar5;
        e eVar6 = new e("BLACK_PREFIX_POLICY", 5, 6, BlackPrefixPolicy.class, dVar2);
        f47855k = eVar6;
        e eVar7 = new e("CONTACTS_POLICY", 6, 7, ContactsPolicy.class, dVar2);
        f47856l = eVar7;
        e eVar8 = new e("BLACK_ADDRESS_POLICY", 7, 8, BlackAddressPolicy.class, dVar2);
        f47857m = eVar8;
        d dVar3 = d.SMS;
        e eVar9 = new e("SERVICE_SMS_POLICY", 8, 9, ServiceSmsPolicy.class, dVar3);
        f47858n = eVar9;
        e eVar10 = new e("KEYWORDS_WHITELIST_POLICY", 9, 10, KeywordsWhiteListPolicy.class, dVar3);
        f47859o = eVar10;
        e eVar11 = new e("CLOUD_WHITELIST_POLICY", 10, 11, CloudWhiteListPolicy.class, dVar2);
        f47860p = eVar11;
        e eVar12 = new e("STRANGER_POLICY", 11, 12, StrangerPolicy.class, dVar2);
        f47861q = eVar12;
        e eVar13 = new e("OVERSEA_POLICY", 12, 13, OverseaPolicy.class, dVar);
        f47862r = eVar13;
        e eVar14 = new e("KEYWORDS_BLACKLIST_POLICY", 13, 14, KeywordsBlackListPolicy.class, dVar3);
        f47863s = eVar14;
        e eVar15 = new e("CLOUD_BLACKLIST_POLICY", 14, 15, CloudBlackListPolicy.class, dVar2);
        f47864t = eVar15;
        e eVar16 = new e("CLOUD_WHITE_KEYWORDS_POLICY", 15, 16, CloudWhiteKeywordsPolicy.class, dVar3);
        f47865u = eVar16;
        e eVar17 = new e("CLOUD_BLACK_KEYWORDS_POLICY", 16, 17, CloudBlackKeywordsPolicy.class, dVar3);
        f47866v = eVar17;
        e eVar18 = new e("SMART_SMS_FILTER_POLICY", 17, 18, SmartSmsFilterPolicy.class, dVar3);
        f47867w = eVar18;
        e eVar19 = new e("CALL_TRANSFER_POLICY", 18, 19, CallTransferPolicy.class, dVar);
        f47868x = eVar19;
        e eVar20 = new e("REPORTED_NUMBER_POLICY", 19, 20, ReportedNumberPolicy.class, dVar);
        f47869y = eVar20;
        f47870z = new e[]{eVar, eVar2, eVar3, eVar4, eVar5, eVar6, eVar7, eVar8, eVar9, eVar10, eVar11, eVar12, eVar13, eVar14, eVar15, eVar16, eVar17, eVar18, eVar19, eVar20};
    }

    private e(String str, int i10, int i11, Class cls, d dVar) {
        super(str, i10);
        this.f47871c = i11;
        this.f47872d = cls;
        this.f47873e = dVar;
    }

    public static e valueOf(String str) {
        return (e) Enum.valueOf(e.class, str);
    }

    public static e[] values() {
        return (e[]) f47870z.clone();
    }
}
