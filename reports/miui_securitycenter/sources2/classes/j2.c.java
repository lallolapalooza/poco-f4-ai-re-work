package j2;

import android.text.TextUtils;
import com.miui.analytics.StatManager;
import miui.telephony.phonenumber.CountryCode;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f47834a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f47835b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f47836c = CountryCode.getNetworkCountryCode();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f47837d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f47838e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f47839f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f47840g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f47841h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f47842i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f47843j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f47844k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f47845l;

    public c(String str, int i10, int i11, String str2, boolean z10, boolean z11, boolean z12) {
        this.f47834a = o2.f.p(str);
        this.f47838e = i10;
        this.f47839f = i11;
        this.f47840g = str2;
        this.f47844k = TextUtils.isEmpty(str2);
        this.f47841h = z10;
        this.f47842i = z11;
        this.f47843j = z12;
        this.f47835b = o2.f.I(this.f47834a);
        this.f47845l = o2.f.g(this.f47834a);
        a();
    }

    private void a() {
        String strSubstring;
        if (TextUtils.equals(this.f47836c, "62")) {
            if (this.f47835b.startsWith(StatManager.PARAMS_SWITCH_OFF)) {
                strSubstring = this.f47835b.substring(1);
            } else {
                strSubstring = StatManager.PARAMS_SWITCH_OFF + this.f47835b;
            }
            this.f47837d = strSubstring;
        }
    }
}
