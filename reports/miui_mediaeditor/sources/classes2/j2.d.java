package j2;

import ch.qos.logback.core.CoreConstants;

/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f11728a;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final int f11729a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f11730b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f11731c;

        public a(int i10, int i11, int i12) {
            this.f11729a = i10;
            this.f11730b = i11;
            this.f11731c = i12;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.f11729a == aVar.f11729a && this.f11730b == aVar.f11730b && this.f11731c == aVar.f11731c;
        }

        public final int hashCode() {
            return Integer.hashCode(this.f11731c) + a6.b.b(this.f11730b, Integer.hashCode(this.f11729a) * 31, 31);
        }

        public final String toString() {
            StringBuilder sb2 = new StringBuilder("FUTexture(texId=");
            sb2.append(this.f11729a);
            sb2.append(", width=");
            sb2.append(this.f11730b);
            sb2.append(", height=");
            return androidx.view.a.i(sb2, this.f11731c, CoreConstants.RIGHT_PARENTHESIS_CHAR);
        }
    }

    public d(a aVar) {
        this.f11728a = aVar;
    }

    public /* synthetic */ d(a aVar, int i10) {
        this((i10 & 1) != 0 ? null : aVar);
    }
}
