package j2;

import ch.qos.logback.core.CoreConstants;
import java.io.File;
import kotlin.jvm.internal.e;
import kotlin.text.j;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f11707a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f11708b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f11709c;

    public a(String str) {
        String string = j.T0(str).toString();
        String separator = File.separator;
        e.e(separator, "separator");
        String strSubstring = string.substring(j.C0(string, separator, 6) + 1);
        e.e(strSubstring, "this as java.lang.String).substring(startIndex)");
        if (j.u0(strSubstring, ".bundle")) {
            strSubstring = strSubstring.substring(0, j.z0(strSubstring, ".bundle", 0, false, 6));
            e.e(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        this.f11707a = str;
        this.f11708b = null;
        this.f11709c = strSubstring;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return e.a(this.f11707a, aVar.f11707a) && e.a(this.f11708b, aVar.f11708b) && e.a(this.f11709c, aVar.f11709c);
    }

    public final int hashCode() {
        int iHashCode = this.f11707a.hashCode() * 31;
        Object obj = this.f11708b;
        return this.f11709c.hashCode() + ((iHashCode + (obj == null ? 0 : obj.hashCode())) * 31);
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder("FUBundleData(path=");
        sb2.append(this.f11707a);
        sb2.append(", supportScenes=");
        sb2.append(this.f11708b);
        sb2.append(", name=");
        return androidx.appcompat.widget.a.f(sb2, this.f11709c, CoreConstants.RIGHT_PARENTHESIS_CHAR);
    }
}
